public class BoardSector {
    private char tile = ' ';
    private boolean beenHit = false;

    public void setTile(char c){
        tile = c;
    }
    public void setHit(){
        this.beenHit = !beenHit;
    }
    public char getTile(){
        return tile;
    }
    public boolean hasBeenHit(){
        return beenHit;
    }

}
