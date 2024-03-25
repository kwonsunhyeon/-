package seoulbus;

public class BusStop {
    private int busId;
    private int arsId;
    private String name;
    private Double x;
    private Double y;
    private String address;
    public BusStop(int busId, int arsId, String name, Double x, Double y, String address) {
        this.busId = busId;
        this.arsId = arsId;
        this.name = name;
        this.x = x;
        this.y = y;
        this.address = address;
    }
    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public int getArsId() {
        return arsId;
    }

    public void setArsId(int arsId) {
        this.arsId = arsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
