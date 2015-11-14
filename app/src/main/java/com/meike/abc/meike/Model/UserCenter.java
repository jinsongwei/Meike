package com.meike.abc.meike.Model;

import com.meike.abc.meike.Model.Tables.PostItem;
import com.meike.abc.meike.Model.Tables.UserInfo;

import java.util.ArrayList;
import java.util.List;

public final class UserCenter {
    private static final List<PostItem> favouriteList = new ArrayList<>();
    private static final List<PostItem> userPostList = new ArrayList<>();

    private UserInfo user;

    public List<PostItem> getFavouriteList() {
        return favouriteList;
    }

    public List<PostItem> getUserPostList() {
        return userPostList;
    }

    public void addFavourite(PostItem post) {
        favouriteList.add(0, post);
    }

    public void removeFavourite(PostItem post) {
        favouriteList.remove(post);
    }

    public void addUserPost(PostItem post) {
        userPostList.add(0, post);
    }

    public void removeUserPost(PostItem post) {
        userPostList.remove(post);
    }

    public void loadFavourite(List<PostItem> toAdd) {
        favouriteList.addAll(0, toAdd);
    }

    public void loadUserPost(List<PostItem> toAdd) {
        userPostList.addAll(0, toAdd);
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
