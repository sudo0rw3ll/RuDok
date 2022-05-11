package dsw.rudok.rudokerrors;

public class RudokGreska {

    private String content;
    private String title;
    private int type;

    public RudokGreska(String content, String title, int type){
        this.content = content;
        this.title = title;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getType() {
        return type;
    }
}
