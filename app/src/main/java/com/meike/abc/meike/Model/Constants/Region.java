package com.meike.abc.meike.Model.Constants;

public enum Region {
    NY("New York", "纽约"),
    SF("San Francisco", "旧金山"),
    LA("Los Angeles", "洛杉矶"),
    SJ("San Jose", "圣荷西"),
    CHI("Chicago", "芝加哥"),
    HON("Honolulu", "檀香山"),
    SD("San Diego", "圣地亚哥"),
    OAK("Oakland", "奥克兰"),
    PHI("Philadelphia", "费城"),
    HOU("Houston", "休斯顿"),
    SEA("Seattle", "西雅图"),
    BOS("Boston", "波士顿"),
    SAC("Sacramento", "萨克拉门托"),
    PLA("Plano", "普莱诺"),
    POR("Portland", "波特兰"),
    AUS("Austin", "奥斯丁"),
    STO("Stockton", "斯托克顿"),
    OC("Orange County", "橙縣"),
    PIT("Pittsburgh", "匹茲堡"),
    RIV("Riverside", "河濱市");

    private final String eng;
    private final String chi;

    Region(String eng, String chi) {
        this.eng = eng;
        this.chi = chi;
    }

    public String getEng() {
        return eng;
    }

    public String getChi() {
        return chi;
    }
}
