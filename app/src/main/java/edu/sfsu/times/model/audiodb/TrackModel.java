package edu.sfsu.times.model.audiodb;

public class TrackModel {
    private final String idTrack;
    private final String idAlbum;
    private final String idArtist;
    private final String idLyric;
    private final String idIMVDB;
    private final String strTrack;
    private final String strAlbum;
    private final String strArtistsAlternative;
    private final String intCD;
    private final String intDuration;
    private final String strGenre;
    private final String strMood;
    private final String strStyle;
    private final String strTheme;
    private final String strDescription;
    private final String strDescriptionEN;
    private final String strDescriptionDE;
    private final String strDescriptionFR;
    private final String strDescriptionCN;
    private final String strDescriptionIT;
    private final String strDescriptionJP;
    private final String strDescriptionRU;
    private final String strDescriptionES;
    private final String strDescriptionPT;
    private final String strDescriptionSE;
    private final String strDescriptionNL;
    private final String strDescriptionHU;
    private final String strDescriptionNO;
    private final String strDescriptionIL;
    private final String strDescriptionPL;
    private final String strTrackThumb;
    private final String strTrack3DCase;
    private final String strTrackLyrics;
    private final String strMusicVid;
    private final String strMusicVidDirector;
    private final String strMusicVidCompany;
    private final String strMusicVidScreen1;
    private final String strMusicVidScreen2;
    private final String strMusicVidScreen3;
    private final String intMusicVidViews;
    private final String intMusicVidLikes;
    private final String intMusicVidDislikes;
    private final String intMusicVidFavorites;
    private final String intMusicVidComments;
    private final String intTrackNumber;
    private final String intLoved;
    private final String intScore;
    private final String intScoreVotes;
    private final String intTotalListeners;
    private final String intTotalPlays;
    private final String strMusicBrainzID;
    private final String strMusicBrainzAlbumID;
    private final String strMusicBrainzArtistID;
    private final String strLocked;

    public TrackModel(String strLocked, String strMusicBrainzArtistID, String strMusicBrainzAlbumID, String strMusicBrainzID, String intTotalPlays, String intTotalListeners, String intScoreVotes, String intScore, String intLoved, String intTrackNumber, String intMusicVidComments, String intMusicVidFavorites, String intMusicVidDislikes, String intMusicVidLikes, String intMusicVidViews, String strMusicVidScreen3, String strMusicVidScreen2, String strMusicVidScreen1, String strMusicVidCompany, String strMusicVidDirector, String strMusicVid, String strTrackLyrics, String strTrack3DCase, String strTrackThumb, String strDescriptionPL, String strDescriptionIL, String strDescriptionNO, String strDescriptionHU, String strDescriptionNL, String strDescriptionSE, String strDescriptionPT, String strDescriptionES, String strDescriptionRU, String strDescriptionJP, String strDescriptionIT, String strDescriptionCN, String strDescriptionFR, String strDescriptionDE, String strDescriptionEN, String strDescription, String strTheme, String strStyle, String strMood, String strGenre, String intDuration, String intCD, String strArtistsAlternative, String strAlbum, String strTrack, String idIMVDB, String idLyric, String idArtist, String idAlbum, String idTrack) {
        this.strLocked = strLocked;
        this.strMusicBrainzArtistID = strMusicBrainzArtistID;
        this.strMusicBrainzAlbumID = strMusicBrainzAlbumID;
        this.strMusicBrainzID = strMusicBrainzID;
        this.intTotalPlays = intTotalPlays;
        this.intTotalListeners = intTotalListeners;
        this.intScoreVotes = intScoreVotes;
        this.intScore = intScore;
        this.intLoved = intLoved;
        this.intTrackNumber = intTrackNumber;
        this.intMusicVidComments = intMusicVidComments;
        this.intMusicVidFavorites = intMusicVidFavorites;
        this.intMusicVidDislikes = intMusicVidDislikes;
        this.intMusicVidLikes = intMusicVidLikes;
        this.intMusicVidViews = intMusicVidViews;
        this.strMusicVidScreen3 = strMusicVidScreen3;
        this.strMusicVidScreen2 = strMusicVidScreen2;
        this.strMusicVidScreen1 = strMusicVidScreen1;
        this.strMusicVidCompany = strMusicVidCompany;
        this.strMusicVidDirector = strMusicVidDirector;
        this.strMusicVid = strMusicVid;
        this.strTrackLyrics = strTrackLyrics;
        this.strTrack3DCase = strTrack3DCase;
        this.strTrackThumb = strTrackThumb;
        this.strDescriptionPL = strDescriptionPL;
        this.strDescriptionIL = strDescriptionIL;
        this.strDescriptionNO = strDescriptionNO;
        this.strDescriptionHU = strDescriptionHU;
        this.strDescriptionNL = strDescriptionNL;
        this.strDescriptionSE = strDescriptionSE;
        this.strDescriptionPT = strDescriptionPT;
        this.strDescriptionES = strDescriptionES;
        this.strDescriptionRU = strDescriptionRU;
        this.strDescriptionJP = strDescriptionJP;
        this.strDescriptionIT = strDescriptionIT;
        this.strDescriptionCN = strDescriptionCN;
        this.strDescriptionFR = strDescriptionFR;
        this.strDescriptionDE = strDescriptionDE;
        this.strDescriptionEN = strDescriptionEN;
        this.strDescription = strDescription;
        this.strTheme = strTheme;
        this.strStyle = strStyle;
        this.strMood = strMood;
        this.strGenre = strGenre;
        this.intDuration = intDuration;
        this.intCD = intCD;
        this.strArtistsAlternative = strArtistsAlternative;
        this.strAlbum = strAlbum;
        this.strTrack = strTrack;
        this.idIMVDB = idIMVDB;
        this.idLyric = idLyric;
        this.idArtist = idArtist;
        this.idAlbum = idAlbum;
        this.idTrack = idTrack;
    }

    public String getIdTrack() {
        return idTrack;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public String getIdLyric() {
        return idLyric;
    }

    public String getIdIMVDB() {
        return idIMVDB;
    }

    public String getStrTrack() {
        return strTrack;
    }

    public String getStrAlbum() {
        return strAlbum;
    }

    public String getStrArtistsAlternative() {
        return strArtistsAlternative;
    }

    public String getIntCD() {
        return intCD;
    }

    public String getIntDuration() {
        return intDuration;
    }

    public String getStrGenre() {
        return strGenre;
    }

    public String getStrMood() {
        return strMood;
    }

    public String getStrStyle() {
        return strStyle;
    }

    public String getStrTheme() {
        return strTheme;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public String getStrDescriptionEN() {
        return strDescriptionEN;
    }

    public String getStrDescriptionDE() {
        return strDescriptionDE;
    }

    public String getStrDescriptionFR() {
        return strDescriptionFR;
    }

    public String getStrDescriptionCN() {
        return strDescriptionCN;
    }

    public String getStrDescriptionIT() {
        return strDescriptionIT;
    }

    public String getStrDescriptionJP() {
        return strDescriptionJP;
    }

    public String getStrDescriptionRU() {
        return strDescriptionRU;
    }

    public String getStrDescriptionES() {
        return strDescriptionES;
    }

    public String getStrDescriptionPT() {
        return strDescriptionPT;
    }

    public String getStrDescriptionSE() {
        return strDescriptionSE;
    }

    public String getStrDescriptionNL() {
        return strDescriptionNL;
    }

    public String getStrDescriptionHU() {
        return strDescriptionHU;
    }

    public String getStrDescriptionNO() {
        return strDescriptionNO;
    }

    public String getStrDescriptionIL() {
        return strDescriptionIL;
    }

    public String getStrDescriptionPL() {
        return strDescriptionPL;
    }

    public String getStrTrackThumb() {
        return strTrackThumb;
    }

    public String getStrTrack3DCase() {
        return strTrack3DCase;
    }

    public String getStrTrackLyrics() {
        return strTrackLyrics;
    }

    public String getStrMusicVid() {
        return strMusicVid;
    }

    public String getStrMusicVidDirector() {
        return strMusicVidDirector;
    }

    public String getStrMusicVidCompany() {
        return strMusicVidCompany;
    }

    public String getStrMusicVidScreen1() {
        return strMusicVidScreen1;
    }

    public String getStrMusicVidScreen2() {
        return strMusicVidScreen2;
    }

    public String getStrMusicVidScreen3() {
        return strMusicVidScreen3;
    }

    public String getIntMusicVidViews() {
        return intMusicVidViews;
    }

    public String getIntMusicVidLikes() {
        return intMusicVidLikes;
    }

    public String getIntMusicVidDislikes() {
        return intMusicVidDislikes;
    }

    public String getIntMusicVidFavorites() {
        return intMusicVidFavorites;
    }

    public String getIntMusicVidComments() {
        return intMusicVidComments;
    }

    public String getIntTrackNumber() {
        return intTrackNumber;
    }

    public String getIntLoved() {
        return intLoved;
    }

    public String getIntScore() {
        return intScore;
    }

    public String getIntScoreVotes() {
        return intScoreVotes;
    }

    public String getIntTotalListeners() {
        return intTotalListeners;
    }

    public String getIntTotalPlays() {
        return intTotalPlays;
    }

    public String getStrMusicBrainzID() {
        return strMusicBrainzID;
    }

    public String getStrMusicBrainzAlbumID() {
        return strMusicBrainzAlbumID;
    }

    public String getStrMusicBrainzArtistID() {
        return strMusicBrainzArtistID;
    }

    public String getStrLocked() {
        return strLocked;
    }
}