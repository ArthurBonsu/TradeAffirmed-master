package  com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class ApprovalID
{
    private String aid,   approvalID,   approverimage,approvername, date, gender, personidentifier, reasons, role, status, statusidentifier, tid,time, uid;

   public ApprovalID() {
    }
    //careful of deliverID and deliveryID
    /// DRIVERS AVAILABLE // DRIVERS WORKING
    public ApprovalID(String  aid,  String approvalID, String  approverimage, String approvername,String  date, String gender,String personidentifier, String reasons, String role,String  status,String statusidentifier,String  tid, String time, String uid) {


        this.aid = aid;
        this.approvalID = approvalID;
        this.approverimage = approverimage;
        this.approvername = approvername;
        this.date = date;
        this.gender = gender;
        this.personidentifier = personidentifier;
        this.reasons = reasons;
        this.role = role;
        this.status = status;
        this.statusidentifier = statusidentifier;
        this.tid = tid;
        this.time = time;
        this.uid = uid;


        this.aid = aid;
        this.approvalID = approvalID;


    }


    public String getaid() {
        return aid;
    }

    public void setaid(String aid) {
        this.aid = aid;
    }


        public String getapprovalID() {
            return approvalID;
        }

        public void setapprovalID(String approvalID) {
            this.approvalID = approvalID;
        }




        public String getapproverimage() {
            return approverimage;
        }

        public void setapproverimage(String approverimage) {
            this.approverimage = approverimage;
        }
    public String getapprovername() {
        return approvername;
    }
    public void setapprovername(String approvername) {
        this.approvername = approvername;
    }

    public String getdate() {
        return date;
    }
    public void setdate(String date) {
        this.date = date;
    }
    public String getgender() {
        return gender;
    }
    public void setgender(String gender) {
        this.gender = gender;
    }
    public String getpersonidentifier() {
        return personidentifier;
    }
    public void setpersonidentifier(String personidentifier) {
        this.personidentifier = personidentifier;
    }
    public String getrole() {
        return role;
    }
    public void setrole(String role) {
        this.role = role;
    }


    public String getstatus() {
        return status;
    }
    public void setstatus(String status) {
        this.status = status;
    }
       public String gettid() {
        return tid;
    }
    public void settid(String tid) {
        this.tid = tid;
    }
    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }
    public String getuid() {
        return uid;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }




}
