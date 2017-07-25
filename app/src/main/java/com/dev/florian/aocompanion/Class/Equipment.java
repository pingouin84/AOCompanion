package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 18/07/2017.
 */

public class Equipment {
    private Item mainHand,offHand,head,armor,shoes,bag,cape,mount,potion,food;

    public static Equipment parse(JSONObject object) {
        Equipment equipment = new Equipment();

        equipment.mainHand = Item.parseKill(object.optJSONObject("MainHand"));
        equipment.offHand = Item.parseKill(object.optJSONObject("OffHand"));
        equipment.head = Item.parseKill(object.optJSONObject("Head"));
        equipment.armor = Item.parseKill(object.optJSONObject("Armor"));
        equipment.shoes = Item.parseKill(object.optJSONObject("Shoes"));
        equipment.bag = Item.parseKill(object.optJSONObject("Bag"));
        equipment.cape = Item.parseKill(object.optJSONObject("Cape"));
        equipment.mount = Item.parseKill(object.optJSONObject("Mount"));
        equipment.potion = Item.parseKill(object.optJSONObject("Potion"));
        equipment.food = Item.parseKill(object.optJSONObject("Food"));

        return equipment;
    }

    public Item getMainHand() {
        return mainHand;
    }

    public void setMainHand(Item mainHand) {
        this.mainHand = mainHand;
    }

    public Item getOffHand() {
        return offHand;
    }

    public void setOffHand(Item offHand) {
        this.offHand = offHand;
    }

    public Item getHead() {
        return head;
    }

    public void setHead(Item head) {
        this.head = head;
    }

    public Item getArmor() {
        return armor;
    }

    public void setArmor(Item armor) {
        this.armor = armor;
    }

    public Item getShoes() {
        return shoes;
    }

    public void setShoes(Item shoes) {
        this.shoes = shoes;
    }

    public Item getBag() {
        return bag;
    }

    public void setBag(Item bag) {
        this.bag = bag;
    }

    public Item getCape() {
        return cape;
    }

    public void setCape(Item cape) {
        this.cape = cape;
    }

    public Item getMount() {
        return mount;
    }

    public void setMount(Item mount) {
        this.mount = mount;
    }

    public Item getPotion() {
        return potion;
    }

    public void setPotion(Item potion) {
        this.potion = potion;
    }

    public Item getFood() {
        return food;
    }

    public void setFood(Item food) {
        this.food = food;
    }
}
