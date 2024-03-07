package tn.SIRIUS.entities;

public class Report {
    private int idReport;
    private Post post;
    private String type;
    private String reason;
    private int nbrReports;

    public int getNbrReports() {
        return nbrReports;
    }

    public void setNbrReports(int nbrReports) {
        this.nbrReports = nbrReports;
    }


    public Report(int idReport, Post post, String type, String reason, int nbrReports) {
        this.idReport = idReport;
        this.post = post;
        this.type = type;
        this.reason = reason;
        this.nbrReports = nbrReports;
    }



    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
