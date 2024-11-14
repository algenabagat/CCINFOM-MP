public class Room {
    private int room_id;
    private int room_no;
    private Roomtype roomtype;
    private float room_price;
    private String room_availability;
    private Hotel hotel;

    // Constructor
    public Room(int room_id, int room_no, Roomtype roomtype, float room_price, String room_availability, Hotel hotel) {
        this.room_id = room_id;
        this.room_no = room_no;
        this.roomtype = roomtype;
        this.room_price = room_price;
        this.room_availability = room_availability;
        this.hotel = hotel;
    }

    // Getters and Setters
    public int getRoomId() {
        return room_id;
    }

    public void setRoomId(int room_id) {
        this.room_id = room_id;
    }

    public int getRoomNo() {
        return room_no;
    }

    public void setRoomNo(int room_no) {
        this.room_no = room_no;
    }

    public Roomtype getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(Roomtype roomtype) {
        this.roomtype = roomtype;
    }

    public float getRoomPrice() {
        return room_price;
    }

    public void setRoomPrice(float room_price) {
        this.room_price = room_price;
    }

    public String getRoomAvailability() {
        return room_availability;
    }

    public void setRoomAvailability(String room_availability) {
        this.room_availability = room_availability;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}