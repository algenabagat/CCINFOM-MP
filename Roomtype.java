public class Roomtype {
    private int roomTypeId;
    private String type;

    public Roomtype(int roomtypeId, String type){
        this.roomTypeId = roomtypeId;
        this.type = type;
    }

    public int getRoomTypeId(){
        return roomTypeId;
    }

    public String getType(){
        return type;
    }

    public void setRoomTypeId(int roomtypeId){
        this.roomTypeId = roomtypeId;
    }

    public void setType(String type){
        this.type = type;
    }
}
