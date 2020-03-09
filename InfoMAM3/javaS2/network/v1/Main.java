package network.v1;

public class Main {
    
    public static void main(String[] args) {
        NewsFeed nf = new NewsFeed();
        MessagePost mp = new MessagePost("Leonardo da Vinci", "Code this...!");
        mp.like();
        mp.like();
        nf.addMessagePost(mp);
        PhotoPost pp = new PhotoPost("Alexander Graham Bell", "handset.png", "Coming soon: the Samsung Galaxy S3.");
        pp.like();
        pp.like();
        nf.addPhotoPost(pp);
        pp.like();
        pp.like();
        nf.show();
    }
}
