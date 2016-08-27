package com.btanabe.adaptivewebscraper.test.utilities;

import com.btanabe.adaptivewebscraper.models.Model;
import com.google.common.eventbus.Subscribe;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brian on 8/7/16.
 */
@Getter
public class CollectedRecordsListener {
    private List<Model> collectedModels = new ArrayList<>();

    @Subscribe
    public void collectedRecord(final Model collectedRecord) {
        collectedModels.add(collectedRecord);
    }

}