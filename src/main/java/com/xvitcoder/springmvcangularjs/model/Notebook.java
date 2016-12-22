package com.xvitcoder.springmvcangularjs.model;

/**
 * Created by Kristina on 23.11.2016.
 */
public class Notebook extends Inventory {

    protected String name;
    protected String location;
    protected String memoryType;
    protected String model;
    protected int additionalFinanceNumber;
    protected String serialNumber;

    public Notebook(){}

    public Notebook(int id, String status, String name, String location, String memoryType,
                        String model, int additionalFinanceNumber, String serialNumber) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.location = location;
        this.memoryType = memoryType;
        this.model = model;
        this.additionalFinanceNumber = additionalFinanceNumber;
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public String getModel() {
        return model;
    }

    public int getAdditionalFinanceNumber() {
        return additionalFinanceNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Notebook{");
        sb.append("id='").append(id).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", memoryType='").append(memoryType).append('\'');
        sb.append(", app.model='").append(model).append('\'');
        sb.append(", additionalFinanceNumber=").append(additionalFinanceNumber);
        sb.append(", serialNumber='").append(serialNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
