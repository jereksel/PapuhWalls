package com.alexcruz.papuhwalls.api;

import android.net.Uri;
import android.support.annotation.NonNull;

public class Wall {

    private final Uri location;
    private final String name;
    private final String author;

    public Wall(@NonNull Uri location, @NonNull String name, @NonNull String author) {
        this.location = location;
        this.name = name;
        this.author = author;
    }

    public Uri getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }
}
