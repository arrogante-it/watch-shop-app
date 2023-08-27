package com.watch.shop.app.model.service;

import com.watch.shop.app.model.repository.Brand;
import com.watch.shop.app.model.repository.Color;
import com.watch.shop.app.model.repository.Mechanism;
import com.watch.shop.app.model.repository.TestDataGenerator;
import com.watch.shop.app.model.repository.Type;
import com.watch.shop.app.model.repository.Watch;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WatchServiceImpl implements WatchService {
    private final List<Watch> watches;

    public WatchServiceImpl() {
        this.watches = new TestDataGenerator().getInitializedWatches();
    }

    @Override
    public void addNewWatch(Brand brand, BigDecimal price, Color color, Mechanism mechanism, Type type, LocalDate arrivalDate) {
        setWatch(brand, price, color, mechanism, type, arrivalDate);
    }

    private void setWatch(Brand brand, BigDecimal price, Color color, Mechanism mechanism, Type type, LocalDate arrivalDate) {
        Watch watch = new Watch.Builder()
                .brand(brand)
                .price(price)
                .color(color)
                .mechanism(mechanism)
                .type(type)
                .arrivalDate(arrivalDate)
                .build();

        new ArrayList<>(watches).add(watch);
    }

    @Override
    public List<Watch> getAllWatches() {
        return List.copyOf(watches);
    }

    @Override
    public void sortByPrice() {
        new ArrayList<>(watches).sort(Comparator.comparing(Watch::getPrice));
    }

    @Override
    public void sortByColor() {
        new ArrayList<>(watches).sort(Comparator.comparing(Watch::getColor));
    }

    @Override
    public void sortByArrivalDate() {
        new ArrayList<>(watches).sort(Comparator.comparing(Watch::getArrivalDate));
    }

    @Override
    public BigDecimal getTotalCost() {
        return new ArrayList<>(watches)
                .stream()
                .map(Watch::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
