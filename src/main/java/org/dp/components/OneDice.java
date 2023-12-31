package org.dp.components;

import org.dp.assets.AssetFactory;
import org.dp.assets.DiceAssets;
import org.dp.logic.GameSystem;
import org.dp.logic.IGameSystem;
import org.dp.utils.AnimationTimeHelper;
import org.dp.utils.Vector2i;
import org.dp.view.Component;
import org.dp.view.ConfirmBox;
import org.dp.view.events.ClickEvent;
import org.dp.view.events.MouseEvent;

import java.awt.*;

//策略模式
//正常情况下是使用一个骰子
public class OneDice extends Component implements Dice {
    private DiceAssets diceAssets = (DiceAssets) AssetFactory.getAsset("diceAssets");
    private boolean isRolling = false;  //是否翻滚，用同一个变量来做到对两个骰子状态的相同判断
    private int lastPoint = 1;  //记录骰子上一次的点数
    private IGameSystem gameSystem;
    private AnimationTimeHelper animationTimeHelper = null;    //记录动画进度
    private double lastProgress = 0;     //记录？？

    //构造函数
    public OneDice(Vector2i p) {
        super(p,new Vector2i(64,64));   //这里初始化位置及该Component组件框大小，p是传入的位置
        gameSystem = GameSystem.get();
        cursorType = Cursor.HAND_CURSOR;
    }

    public int getRandomDicePoint(){    //随机生成骰子的点数
        lastPoint = (int)(Math.random() * 6) + 1;
        return lastPoint;
    }
    @Override
    public int getDicePointSum() {     //返回两个骰子的总点数
        return lastPoint;
    }

    @Override
    public void drawMe(Graphics graphics) {
        Vector2i p = getAbsPosition();
        if(!isRolling){     //如果骰子不在翻滚状态，画骰子的图
            graphics.drawImage(diceAssets.diceImage[lastPoint - 1], p.x,p.y, 64,64,null);
        }
        else {
            double nowProgress = animationTimeHelper.getBezierProgress();
            if (nowProgress > lastProgress + 0.05) {
                lastPoint = getRandomDicePoint();
                lastProgress += 0.05;
            }
            graphics.drawImage(diceAssets.diceImage[lastPoint - 1], p.x, p.y, 64, 64, null);
            if (nowProgress == 1) {
                isRolling = false;
                lastPoint = gameSystem.getNextDicePoint();    //这里获取骰子的点数并展示出来
                ConfirmBox c = new ConfirmBox("你骰到了" + getDicePointSum() + "点!");
                c.show();
            }
        }
    }

    public boolean onMouseEventMe(MouseEvent e){
        if(!(e instanceof ClickEvent))
            return true;
        if(!gameSystem.canRollDice() || isRolling){
            ConfirmBox c = new ConfirmBox("现在还不能掷骰子哦~");
            c.show();
            return true;
        }else{
            lastProgress = 0.0;
            isRolling = true;
            animationTimeHelper = new AnimationTimeHelper(2000);
            animationTimeHelper.start();
        }
        return true;
    }
    //一个骰子的情况下，我们要实现，玩家点击骰子，就可以进行骰子的旋转
    //因此，首先，我们要写drawMe函数来画出骰子
    //然后，还要能做到鼠标点击骰子之后对应的事件
    //按理来说比较简单
    //直接抄就行

}
