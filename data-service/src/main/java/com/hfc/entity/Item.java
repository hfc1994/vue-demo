package com.hfc.entity;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 * 标签列表的实体类
 * 标签列表的结构类似如下，只有子节点可以点击跳转
 * ---豆瓣
 * ------豆瓣电影
 * ------豆瓣图书
 * ------豆瓣音乐
 * ---猫眼
 * ------猫眼电影
 */
public class Item
{
    private int id = 0;
    private String item = "";
    private String item_id = "";
    private int item_level = 0;
    private String item_pre = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getItem_level() {
        return item_level;
    }

    public void setItem_level(int item_level) {
        this.item_level = item_level;
    }

    public String getItem_pre() {
        return item_pre;
    }

    public void setItem_pre(String item_pre) {
        this.item_pre = item_pre;
    }

    // 考虑到字段会更新，那么每次都得重新组装一次
    private String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(this.item).append(this.item_id)
                .append(this.item_level).append(this.item_pre);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.getContent().hashCode();
    }

    @Override
    public String toString() {
        return this.getContent();
    }
}
