package talitha_koum.milipade.com.app.vhuka.models;

/**
 * Created by TALITHA_KOUM on 3/4/2019.
 * file for the  vhuka. project
 * in talitha_koum.milipade.com.app.vhuka.models
 */
public class Photos {

    private int albumId;
    private int  id;
    private String  title;
    private String url;
    private String  thumbnailUrl;

    public Photos() {
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }



}
