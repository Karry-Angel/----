package org.dp.logic;

import org.dp.components.MapComponent;
import org.dp.components.tiles.PlaceTileComponent;
import org.dp.components.tiles.StartTileComponent;
import org.dp.components.tiles.TileFactory;
import org.dp.utils.Vector2i;

// 使用生成器模式
public class MapBuilder {

    // 设想是通过json生成地图，具体请诸位实现，现在的地图是硬编码进去的
    public MapBuilder(String mapConfigJson){

    }

    public GameMap build(Vector2i pos){
        TileFactory tileFactory = new TileFactory();
        GameMap ans = new GameMap();
        ans.mapComponent = new MapComponent(pos);
        // 把地图读入然后设置位置，应该从gameSystem中获得
        Tile tile1 = new Tile();
        StartTileComponent startTileComponent = (StartTileComponent) tileFactory.getTile(TileFactory.TileType.START, new Vector2i(200,200), 0);
        tile1.component = startTileComponent;
        ans.firstTile = tile1;

        PlaceTileComponent placeTileComponent1 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(100,200), 1);
        Tile tile2 = new Tile();
        tile2.component = placeTileComponent1;
        tile1.next = tile2;
        tile2.prev = tile1;

        PlaceTileComponent placeTileComponent2 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(0,200), 2);
        Tile tile3 = new Tile();
        tile3.component = placeTileComponent2;
        tile2.next = tile3;
        tile3.prev = tile2;

        PlaceTileComponent placeTileComponent3 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(0,100), 3);
        Tile tile4 = new Tile();
        tile4.component = placeTileComponent3;
        tile3.next = tile4;
        tile4.prev = tile3;

        PlaceTileComponent placeTileComponent4 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(0,0), 4);
        Tile tile5 = new Tile();
        tile5.component = placeTileComponent4;
        tile4.next = tile5;
        tile5.prev = tile4;

        PlaceTileComponent placeTileComponent5 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(100,0), 5);
        Tile tile6 = new Tile();
        tile6.component = placeTileComponent5;
        tile5.next = tile6;
        tile6.prev = tile5;

        PlaceTileComponent placeTileComponent6 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(200,0), 6);
        Tile tile7 = new Tile();
        tile7.component = placeTileComponent6;
        tile6.next = tile7;
        tile7.prev = tile6;

        PlaceTileComponent placeTileComponent7 = (PlaceTileComponent) tileFactory.getTile(TileFactory.TileType.PLACE, new Vector2i(200,100),7);
        Tile tile8 = new Tile();
        tile8.component = placeTileComponent7;
        tile7.next = tile8;
        tile8.prev = tile7;

        tile8.next = tile1;
        tile1.prev = tile8;

        ans.mapComponent.addComponent(startTileComponent);
        ans.mapComponent.addComponent(placeTileComponent1);
        ans.mapComponent.addComponent(placeTileComponent2);
        ans.mapComponent.addComponent(placeTileComponent3);
        ans.mapComponent.addComponent(placeTileComponent4);
        ans.mapComponent.addComponent(placeTileComponent5);
        ans.mapComponent.addComponent(placeTileComponent6);
        ans.mapComponent.addComponent(placeTileComponent7);
        ans.mapComponent.setHitBoxSize(new Vector2i(300,300));
        Tile now = tile1.next;
        while(now != tile1){
            now = now.next;
        }

        return ans;
    }
}
