public class Roomtype {
    private int roomtype_id;
    String type;

    public Roomtype(int roomtypeId, String type){
        this.roomtype_id = roomtypeId;
        this.type = type;
    }

    public int getRoomtypeId(){
        return roomtype_id;
    }

    public String getType(){
        return type;
    }

    public void setRoomtypeId(int roomtypeId){
        this.roomtype_id = roomtypeId;
    }

    public void setType(String type){
        this.type = type;
    }
}
