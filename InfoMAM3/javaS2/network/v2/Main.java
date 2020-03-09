package network.v2;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        NewsFeed nf = new NewsFeed();
        MessagePost mp = new MessagePost("Leonardo da Vinci", "Code this...!");
        mp.like();
        mp.like();
        nf.addPost(mp);
        PhotoPost pp = new PhotoPost("Alexander Graham Bell", "handset.png", "Coming soon: the Samsung Galaxy S3.");
        pp.like();
        pp.like();
        nf.addPost(pp);
        pp.like();
        pp.like();
        nf.show();
    }
}
