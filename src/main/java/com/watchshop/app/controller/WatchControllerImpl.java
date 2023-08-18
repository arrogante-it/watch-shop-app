package com.watchshop.app.controller;

import com.watchshop.app.model.Brand;
import com.watchshop.app.model.Color;
import com.watchshop.app.model.Watch;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class WatchControllerImpl implements WatchController {
    private List<Watch> watches;

    public WatchControllerImpl(List<Watch> watches) {
        this.watches = watches;
    }

    @Override
    public void initializeWatches() {
        addNewWatch(Brand.BROLEX, new BigDecimal(150.0), Color.BLACK, new Date());
        addNewWatch(Brand.ANOT_ROLEX, new BigDecimal(100.0),Color.WHITE , new Date());
        addNewWatch(Brand.CANY_ROLEX, new BigDecimal(200.0), Color.SILVER, new Date());

    }

    public String inputBrand() {
        return new Scanner(System.in).nextLine();
    }

    public BigDecimal inputPrice() {
        Scanner scanner = new Scanner(System.in);
        BigDecimal price = scanner.nextBigDecimal();
        scanner.nextLine();
        return price;
    }

    public String inputColor() {
        return new Scanner(System.in).nextLine();
    }

    public String inputArrivalDate() {
        return new Scanner(System.in).nextLine();
    }

    public Date inputAndParseArrivalDate() {
        String arrivalDateStr = inputArrivalDate();
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(arrivalDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addNewWatch(Brand brand, BigDecimal price, Color color, Date arrivalDate) {
        setWatch(brand, price, color, arrivalDate);
    }

    private void setWatch(Brand brand, BigDecimal price, Color color, Date arrivalDate) {
        Watch watch = new Watch()
                .setBrand(brand)
                .setPrice(price)
                .setColor(color)
                .setArrivalDate(arrivalDate);
        watches.add(watch);
    }

    @Override
    public List<Watch> getAllWatches() {
        return watches;
    }

    @Override
    public void sortByPrice() {
        watches.sort(Comparator.comparing(Watch::getPrice));
    }

    @Override
    public void sortByColor() {
        watches.sort(Comparator.comparing(Watch::getColor));
    }

    @Override
    public void sortByArrivalDate() {
        watches.sort(Comparator.comparing(Watch::getArrivalDate));
        ;
    }

    @Override
    public BigDecimal getTotalCost() {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (Watch watch : watches) {
            totalCost = totalCost.add(watch.getPrice());
        }
        return totalCost;
    }
}
