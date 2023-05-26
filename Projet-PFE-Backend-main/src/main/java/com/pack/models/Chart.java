package com.pack.models;

import java.util.List;

public class Chart {
    private List<String> axisXData;
    private List<BarChart> series;
    public Chart() {
    }
    public List<String> getAxisXData() {
        return axisXData;
    }
    public void setAxisXData(List<String> axisXData) {
        this.axisXData = axisXData;
    }
    public List<BarChart> getSeries() {
        return series;
    }
    public void setSeries(List<BarChart> series) {
        this.series = series;
    }
}
