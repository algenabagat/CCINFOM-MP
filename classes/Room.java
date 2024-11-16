package classes;

public class Room {
    private int roomId;
    private int roomNo;
    private int roomTypeId;
    private float roomPrice;
    private String roomAvailability;
    private int hotelId;

    // Constructor
    public Room(int roomId, int roomNo, int roomTypeId, float roomPrice, String roomAvailability, int hotelId) {
        this.roomId = roomId;
        this.roomNo = roomNo;
        this.roomTypeId = roomTypeId;
        this.roomPrice = roomPrice;
        this.roomAvailability = roomAvailability;
        this.hotelId = hotelId;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public float getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(float roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getRoomAvailability() {
        return roomAvailability;
    }

    public void setRoomAvailability(String roomAvailability) {
        this.roomAvailability = roomAvailability;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }
}