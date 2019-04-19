package com.urfan.vehicleservice.model;

import io.swagger.annotations.ApiModelProperty;

public class VehicleStatus {
    @ApiModelProperty(notes = "The number of Online vehicle.")
    private long onlineCount;

    @ApiModelProperty(notes = "The number of Offline vehicle.")
    private long offlineCount;

    @ApiModelProperty(notes = "The total number of vehicles.")
    private long total;


    public long getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(long onlineCount) {
        this.onlineCount = onlineCount;
    }

    public long getOfflineCount() {
        return offlineCount;
    }

    public void setOfflineCount(long offlineCount) {
        this.offlineCount = offlineCount;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
