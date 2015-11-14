package com.meike.abc.meike.Model;


import com.meike.abc.meike.Model.Constants.Region;
import com.meike.abc.meike.Model.Constants.TableType;
import com.meike.abc.meike.Model.Support.PostAttribute;
import com.meike.abc.meike.Model.Tables.PostItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public final class DataCenter extends Observable {

    private static final List<PostItem> housingList = new ArrayList<>(50);
    private static final List<PostItem> autoList = new ArrayList<>(50);
    private static final List<PostItem> jobsList = new ArrayList<>(50);
    private static final List<PostItem> saleList = new ArrayList<>(50);
    private static final List<PostItem> otherList = new ArrayList<>(50);
    private static final List<PostItem> filterList = new ArrayList<>(50);

    private Region currentRegion;
    private TableType currentType;
    private List<PostItem> currentList;
    private List<PostAttribute> currentFilter;

    public List<PostItem> getCurrentList() {
        return currentList;
    }

    public List<PostItem> getFilterList() {
        return filterList;
    }

    public void setCurrentFilter(List<PostAttribute> filter) {
        this.currentFilter = filter;
        filterList.clear();
    }

    public void addFirst(List<PostItem> newList) {
        currentList.addAll(0, newList);
    }

    public void addLast(List<PostItem> newList) {
        currentList.addAll(newList);
    }

    public void addFirst(List<PostItem> newList, TableType type) {
        //noinspection ConstantConditions
        findList(type).addAll(0, newList);
    }

    public void addLast(List<PostItem> newList, TableType type) {
        //noinspection ConstantConditions
        findList(type).addAll(newList);
    }

    public void setCurrentType(TableType type) {
        currentType = type;
        currentList = findList(type);
    }

    private List<PostItem> findList(TableType type) {
        switch (type) {
            case Housing:
                return housingList;
            case Auto:
                return autoList;
            case Jobs:
                return jobsList;
            case Sale:
                return saleList;
            case Other:
                return otherList;
        }
        return null;
    }

    public Region getCurrentRegion() {
        return currentRegion;
    }

    public void setCurrentRegion(Region currentRegion) {
        this.currentRegion = currentRegion;
    }

    public TableType getCurrentType() {
        return currentType;
    }

    public List<PostAttribute> getCurrentFilter() {
        return currentFilter;
    }
}
