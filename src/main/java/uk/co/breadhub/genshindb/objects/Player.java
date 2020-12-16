package uk.co.breadhub.genshindb.objects;

public class Player {

    private String username;
    private String region = null;
    private String uid;

    public Player(){

    }

    public Player(String username, String uid) {
        this.uid = uid;
        this.username = username;

        //parse region from uid
        switch (Integer.parseInt(uid.substring(0,1))){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                this.region = "Unknown Region";
                break;
            case 6:
                this.region = "America";
                break;
            case 7:
                this.region = "Europe";
                break;
            case 8:
                this.region = "Asia";
                break;
            case 9:
                this.region = "TW, HK, MO";
                break;
        }
    }

    public String getRegion() {
        return region;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }
}
