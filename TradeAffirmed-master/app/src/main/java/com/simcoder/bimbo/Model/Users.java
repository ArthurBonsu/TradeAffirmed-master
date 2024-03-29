package com.simcoder.bimbo.Model;

import com.simcoder.bimbo.instagram.Models.Like;

import java.util.List;

public class Users {
    // securityinfostatus
    // personalinfostatus
    // residenceinfostatus
    // backgroundinfostatus
    // securityinfostatus
    String securityinfostatus, personalinfostatus, residenceinfostatus, backgroundinfostatus;

    private String desc, image, title, name, age, phone, discount, pid, price, quantity, tid, traderID, commentkey, likeid;

    private String address, amount, city, date, state, distance, mode, number, shippingcost, time, delivered;

    private String count, condition, uid;
    private String password;
    private String coverimage, g, one, zero, customerId, customerRideId, destination, destinationLat, destinationLng;
    private String driverFoundID, customer, driver, predictDistance, lat, lng, rating, triptime;
    private String job, quote, role;
    String natidimage, gpscode, gpsimage;
    String reasons;
    private String car, descriptions;
    String approverimage, aid, approvername, approvalID, status;

    private String field, followersname, customerPaid, driverPaidOut;
    private String timestamp, operations, reviewBy, residences, service, setinformations;
    private String baseprice;
    private String comment;
    String approved;
    String likenumber;
    String response;

    private List<Like> likes;
    String subject;
    String photoid;
    String orderkey;
    String reply;
    String replyid;
    String traderimage;
    String tradername;

    String pname;
    String pimage;
    String newornot;
    String paymentkey;
    String idcode;
    String idimage;
    String email;
    String gender;
    String pause;
    String reject;
    String approve;
    String street;
    String country;

    String auxname;
    String auxphone;
    String auxemail;
    String auxid;
    String typeofid;
    String auxcountry;
    String personalinfo, residenceinfo, backgroundinfo, securityinfo;


    //pause
    // reject
    //approve
    //email
    //gender
    //response
    //age


    public Users(String uid, String name, String image, String phone, String email, String gender, String age) {
    }


    public Users(
            String desc, String image, String title, String name, String phone, String discount, String pid, String price, String quantity, String tid, String traderID,

            String address, String amount, String city, String date, String state, String distance, String mode, String number, String shippingcost, String time,

            String count, String condition, String uid,
            String password,
            String coverimage, String g, String one, String zero, String customerId, String customerRideId, String destination, String destinationLat, String destinationLng,
            String driverFoundID, String customer, String driver, String predictDistance, String lat, String lng, String rating, String triptime,
            String job, String quote, String role, String car, String descriptions, String field, String followersname, String customerPaid, String driverPaidOut,
            String timestamp, String operations, String reviewBy, String residences, String service, String setinformations, String baseprice, String commentkey, String likeid, String newornot, String paymentkey, String likenumber
    ) {


        this.desc = desc;
        this.image = image;
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.discount = discount;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
        this.tid = tid;
        this.traderID = traderID;
        this.address = address;
        this.amount = amount;
        this.city = city;
        this.date = date;
        this.state = state;
        this.distance = distance;
        this.mode = mode;
        this.number = number;
        this.shippingcost = shippingcost;
        this.time = time;

        this.count = count;
        this.condition = condition;
        this.uid = uid;
        this.password = password;
        this.coverimage = coverimage;
        this.g = g;

        this.one = one;
        this.zero = zero;
        this.customerId = customerId;
        this.customerRideId = customerRideId;
        this.destination = destination;
        this.destinationLat = destinationLat;
        this.destinationLng = destinationLng;
        this.driverFoundID = driverFoundID;
        this.customer = customer;
        this.driver = driver;
        this.predictDistance = predictDistance;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.triptime = triptime;
        this.job = job;
        this.quote = quote;
        this.role = role;
        this.car = car;
        this.descriptions = descriptions;
        this.field = field;
        this.followersname = followersname;
        this.customerPaid = customerPaid;
        this.driverPaidOut = driverPaidOut;
        this.timestamp = timestamp;
        this.operations = operations;
        this.reviewBy = reviewBy;
        this.residences = residences;
        this.service = service;
        this.setinformations = setinformations;
        this.baseprice = baseprice;
        this.commentkey = commentkey;
        this.likeid = likeid;
        this.newornot = newornot;
        this.paymentkey = paymentkey;
        this.likenumber = likenumber;
        this.age = age;
        this.response = response;

    }

    public Users(String comment, String uid, List<Like> likes, String date, String time, String tid, String name, String number, String subject, String likeid, String pid, String commentkey, String photoid, String orderkey,
                 String reply, String replyid, String traderimage, String tradername, String pname, String pimage, String image
    ) {
        this.comment = comment;
        this.uid = uid;
        this.likes = likes;
        this.date = date;
        this.likeid = likeid;
        this.time = time;
        this.tid = tid;
        this.name = name;
        this.number = number;
        this.subject = subject;
        this.pid = pid;
        this.commentkey = commentkey;
        this.photoid = photoid;

        this.orderkey = orderkey;
        this.reply = reply;
        this.replyid = replyid;
        this.traderimage = traderimage;
        this.pname = pname;
        this.image = image;
        this.photoid = photoid;
        this.pimage = pimage;
        this.tradername = tradername;

    }

    public Users(String date, String time, String tid, String traderimage, String tradername, String address, String amount, String city, String delivered, String distance, String image, String uid, String name, String mode, String number, String phone, String quantity, String shippingcost, String state) {

        this.desc = desc;
        this.image = image;
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.discount = discount;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
        this.tid = tid;
        this.traderID = traderID;
        this.address = address;
        this.amount = amount;
        this.city = city;
        this.date = date;
        this.state = state;
        this.distance = distance;
        this.mode = mode;
        this.number = number;
        this.shippingcost = shippingcost;
        this.time = time;
        this.delivered = delivered;


        this.count = count;
        this.condition = condition;
        this.uid = uid;
        this.password = password;
        this.coverimage = coverimage;
        this.g = g;
        this.traderimage = traderimage;
        this.tradername = tradername;

        this.one = one;
        this.zero = zero;
        this.customerId = customerId;
        this.customerRideId = customerRideId;
        this.destination = destination;
        this.destinationLat = destinationLat;
        this.destinationLng = destinationLng;
        this.driverFoundID = driverFoundID;
        this.customer = customer;
        this.driver = driver;
        this.predictDistance = predictDistance;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.triptime = triptime;
        this.job = job;
        this.quote = quote;
        this.role = role;
        this.car = car;
        this.descriptions = descriptions;
        this.field = field;
        this.followersname = followersname;
        this.customerPaid = customerPaid;
        this.driverPaidOut = driverPaidOut;
        this.timestamp = timestamp;
        this.operations = operations;
        this.reviewBy = reviewBy;
        this.residences = residences;
        this.service = service;
        this.setinformations = setinformations;
        this.baseprice = baseprice;
        this.commentkey = commentkey;
        this.likeid = likeid;


    }

    public Users(String date, String time, String tid, String traderimage, String tradername, String address, String amount, String city, String delivered, String distance, String image, String uid, String name, String mode, String number, String phone, String quantity, String shippingcost, String state, String approved) {

        this.desc = desc;
        this.image = image;
        this.title = title;
        this.name = name;
        this.phone = phone;
        this.discount = discount;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
        this.tid = tid;
        this.traderID = traderID;
        this.address = address;
        this.amount = amount;
        this.city = city;
        this.date = date;
        this.approved = approved;
        this.distance = distance;
        this.mode = mode;
        this.number = number;
        this.shippingcost = shippingcost;
        this.time = time;
        this.delivered = delivered;
        this.state = state;


        this.count = count;
        this.condition = condition;
        this.uid = uid;
        this.password = password;
        this.coverimage = coverimage;
        this.g = g;
        this.traderimage = traderimage;
        this.tradername = tradername;

        this.one = one;
        this.zero = zero;
        this.customerId = customerId;
        this.customerRideId = customerRideId;
        this.destination = destination;
        this.destinationLat = destinationLat;
        this.destinationLng = destinationLng;
        this.driverFoundID = driverFoundID;
        this.customer = customer;
        this.driver = driver;
        this.predictDistance = predictDistance;
        this.lat = lat;
        this.lng = lng;
        this.rating = rating;
        this.triptime = triptime;
        this.job = job;
        this.quote = quote;
        this.role = role;
        this.car = car;
        this.descriptions = descriptions;
        this.field = field;
        this.followersname = followersname;
        this.customerPaid = customerPaid;
        this.driverPaidOut = driverPaidOut;
        this.timestamp = timestamp;
        this.operations = operations;
        this.reviewBy = reviewBy;
        this.residences = residences;
        this.service = service;
        this.setinformations = setinformations;
        this.baseprice = baseprice;
        this.commentkey = commentkey;
        this.likeid = likeid;


    }


    public Users(String orderkey, String date, String time, String tid, String traderimage, String tradername, String address, String amount, String city, String delivered, String distance, String image, String uid, String name, String mode, String number, String phone, String quantity, String state, String shippingcost, String approved) {
        this.orderkey = orderkey;
        this.date = date;
        this.time = time;
        this.tid = tid;
        this.traderimage = traderimage;
        this.tradername = tradername;
        this.address = address;
        this.amount = amount;
        this.city = city;
        this.delivered = delivered;
        this.distance = distance;
        this.image = image;
        this.uid = uid;
        this.name = name;
        this.mode = mode;
        this.number = number;
        this.phone = phone;
        this.quantity = quantity;
        this.state = state;
        this.shippingcost = shippingcost;
        this.approved = approved;
    }


    public Users(String uid, String name, String address, String street, String gpscode, String country) {
        this.uid = uid;
        this.name = name;
        this.address = address;
        this.street = street;
        this.gpscode = gpscode;
        this.country = country;
    }

    // this.natidimage = natidimage;
    //   this.gpscode = gpscode;
    //  this.gpsimage = gpsimage;
    public Users(String natidimage) {
        this.natidimage = natidimage;
    }

    public Users(String gpscode, String gpsimage) {
        this.gpscode = gpscode;
        this.gpsimage = gpsimage;
    }

    public Users(String uid, String name, String gpsimage, String gpscode, String natidimage) {
        this.uid = uid;
        this.name = name;
        this.gpsimage = gpsimage;
        this.gpscode = gpscode;
        this.natidimage = natidimage;
    }

    public Users(String personalinfo, String residenceinfo, String backgroundinfo, String securityinfo) {
        this.personalinfo = personalinfo;
        this.residenceinfo = residenceinfo;
        this.backgroundinfo = backgroundinfo;
        this.securityinfo = securityinfo;
    }

    public Users(String uid, String name, String image, String phone, String email, String gender, String age, String inforesponse, String approvalactivitytype) {
        this.uid = uid;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        if (approvalactivitytype == "personalinfoapproveact") {
            this.personalinfostatus = inforesponse;
        }
        if (approvalactivitytype == "backgroundinfoapproveact") {
            this.backgroundinfostatus = inforesponse;
        }
        if (approvalactivitytype == "residentialinfoapproveact") {
            this.residenceinfostatus = inforesponse;
        }

        if (approvalactivitytype == "securityinfoapproveact") {
            this.securityinfostatus = inforesponse;
        }
    }

    public Users(String uid, String name, String address, String street, String gpscode, String country, String inforesponse, String securitycheckapprove) {
        this.uid = uid;
        this.name = name;
        this.image = image;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.securityinfostatus = inforesponse;

    }

    public Users(String uid, String name, String reasons) {
        this.uid = uid;
        this.name = name;
        this.reasons = reasons;


    }






    // securityinfostatus
    // personalinfostatus
    // residenceinfostatus
    // backgroundinfostatus
    // securityinfostatus

    public String getapproverimage() {
        return approverimage;
    }


    public void setapproverimage(String approverimage) {
        this.approverimage = approverimage;
    }

    public String getaid() {
        return aid;
    }


    public void setaid(String aid) {
        this.aid = aid;
    }


    public String getapprovername() {
        return approvername;
    }


    public void setapprovername(String approvername) {
        this.approvername = approvername;
    }

    public String getApprovalID() {
        return approvalID;
    }


    public void setApprovalID(String approvalID) {
        this.approvalID = approvalID;
    }
    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }
    public String getreasons() {
        return reasons;
    }


    public void setreasons(String reasons) {
        this.reasons = reasons;
    }


    public String getpersonalinfo() {
        return personalinfo;
    }


    public void setpersonalinfo(String personalinfo) {
        this.personalinfo = personalinfo;
    }

    public String getresidenceinfo() {
        return residenceinfo;
    }


    public void setresidenceinfo(String residenceinfo) {
        this.residenceinfo = residenceinfo;
    }

    public String getbackgroundinfo() {
        return backgroundinfo;
    }


    public void setbackgroundinfo(String backgroundinfo) {
        this.backgroundinfo = backgroundinfo;
    }

    public String getsecurityinfo() {
        return securityinfo;
    }


    public void setsecurityinfo(String securityinfo) {
        this.securityinfo = securityinfo;
    }

    public String getnatidimage() {
        return natidimage;
    }


    public void setnatidimage(String natidimage) {
        this.natidimage = natidimage;
    }



    public String getgpsimage() {
        return gpsimage;
    }


    public void setgpsimage(String street) {
        this.gpsimage = gpsimage;
    }


    public String getstreet() {
        return street;
    }


    public void setstreet(String street) {
        this.street = street;
    }
    public String getgpscode() {
        return gpscode;
    }


    public void setgpscode(String gpscode) {
        this.gpscode= gpscode;
    }
    public String getcountry() {
        return country;
    }


    public void setcountry(String country) {
        this.country= country;
    }


    public String getreject() {
        return reject;
    }


    public void setreject(String reject) {
        this.reject = reject;
    }


    public void setapprove(String approve) {
        this.approve = approve;
    }
    public String getapprove() {
        return approve;
    }
    public String getpause() {
        return pause;
    }

    public void setpause(String pause) {
        this.pause = pause;
    }

     public String getemail() {
        return email;
    }


    public void setemail(String email) {
        this.email = email;
    }

      public void setgender(String gender) {
        this.gender = gender;
    }




    public String getgender() {
        return gender;
    }


    public void setage(String age) {
        this.age = age;
    }
    public String getage() {
        return age;
    }


    public void settradername(String tradername) {
        this.tradername = tradername;
    }

    public String getorderkey() {
        return orderkey;
    }


    public String getreply() {
        return reply;
    }


    public String getreplyid() {
        return replyid;
    }

    public String getpaymentkey() {
        return paymentkey;
    }

    public void setpaymentkey(String paymentkey) {
        this.paymentkey = paymentkey;
    }



    public void setreplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getapproved() {
        return approved;
    }

    public void setnewornot(String newornot) {
        this.newornot = newornot;
    }

    public String getnewornot() {
        return newornot;
    }



    public void setapproved(String approved) {
        this.approved = approved;
    }
    public String gettraderimage() {
        return traderimage;
    }

    public void settraderimage(String traderimage) {
        this.traderimage = traderimage;
    }


    public String getpname() {
        return pname;
    }

    public void setpname(String pname) {
        this.pname = pname;
    }


    public String getlikenumber(){return likenumber;}
    public void setlikenumber(String likenumber){this.likenumber = likenumber;}

    public void setreply(String reply) {
        this.reply = reply;
    }

    public void setorderkey(String orderkey) {
        this.orderkey = orderkey;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getcommentkey() {
        return commentkey;
    }

    public void setcommentkey(String commentkey) {
        this.commentkey = commentkey;
    }


    public String getuid() {
        return uid;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getLikeid(String likeid) {
        return likeid;
    }
    public void setLikeid(String likeid) {
        this.likeid = likeid;
    }







    public String getsubject() {
        return subject;
    }

    public void setsubject(String subject) {
        this.subject = subject;
    }



    public String getphotoid() {
        return photoid;
    }

    public void setphotoid(String photoid) {
        this.photoid = photoid;
    }






    public String getPhone() {
        return phone;
    }
    public void setphone(String phone) {
        this.phone = phone;
    }

    public String getdiscount(String discount) {
        return discount;
    }
    public void setdiscount(String discount) {
        this.discount = discount;
    }




    public String  getdelivered() {
        return delivered;
    }
    public void setdelivered(String delivered) {
        this.delivered = delivered;
    }

    public String  getcity() {
        return city;
    }
    public void setcity(String city) {
        this.city = city;
    }

    public String getone() {
        return one;
    }
    public void setone(String one) {
        this.one = one;
    }

    public String getzero() {
        return zero;
    }
    public void setzero(String zero) {
        this.zero = zero;
    }
    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }


    public String getState() {
        return state;
    }

    public void setstate(String state) {
        this.state = state;
    }

    public String getdate() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String gettime() {
        return time;
    }

    public void settime(String time) {
        this.time = time;
    }

    public String getamount() {
        return amount;
    }

    public void setamount(String amount) {
        this.amount = amount;
    }


    public String getsecurityinfostatus() {
        return securityinfostatus;
    }

    public void setsecurityinfostatus(String securityinfostatus) {
        this.securityinfostatus = securityinfostatus;
    }
    public String getpersonalinfostatus() {
        return personalinfostatus;
    }

    public void setpersonalinfostatus(String personalinfostatus) {
        this.personalinfostatus = personalinfostatus;
    }

    public String getresidenceinfostatus() {
        return residenceinfostatus;
    }

    public void setresidenceinfostatus(String residenceinfostatus) {
        this.residenceinfostatus = residenceinfostatus;
    }
    public String getbackgroundinfostatus() {
        return backgroundinfostatus;
    }

    public void setbackgroundinfostatus(String backgroundinfostatus) {
        this.backgroundinfostatus = backgroundinfostatus;
    }
    public String getquantity() {
        return quantity;
    }

    public void setquantity(String quantity) {
        this.quantity = quantity;
    }

    public String getshippingcost() {
        return shippingcost;
    }

    public void setshippingcost(String shippingcost) {
        this.shippingcost = shippingcost;
    }


    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }


    public String getdistance() {
        return distance;
    }

    public void setdistance(String distance) {
        this.distance = distance;
    }

    public String getmode() {
        return mode;
    }

    public void setmode(String mode) {
        this.mode = mode;
    }

    public String getnumber() {
        return number;
    }

    public void setnumber(String number) {
        this.number = number;
    }

    public String getcount() {
        return count;
    }

    public void setcount(String count) {
        this.count = count;
    }


    public String gettraderID() {
        return traderID;
    }

    public void settraderID(String traderID) {
        this.traderID = traderID;
    }


    public String getpid() {
        return pid;
    }

    public void setpid(String pid) {
        this.pid = pid;
    }

    public String getcoverimage() {
        return coverimage;
    }

    public void setcoverimage(String coverimage) {
        this.coverimage = coverimage;
    }

    public String getg() {
        return g;
    }

    public void setg(String g) {
        this.g = g;
    }


    public String getcustomerId() {
        return customerId;
    }

    public void setcustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getcustomerRideId() {
        return customerRideId;
    }

    public void setcustomerRideId(String customerRideId) {
        this.customerRideId = customerRideId;
    }

    public String getdestination() {
        return destination;
    }

    public void setdestination(String destination) {
        this.destination = destination;
    }


    public String getdestinationLat() {
        return destinationLat;
    }

    public void setdestinationLat(String destinationLat) {
        this.destinationLat = destinationLat;
    }

    public String getdestinationLng() {
        return destinationLng;
    }

    public void setdestinationLng(String destinationLng) {
        this.destinationLng = destinationLng;
    }


    public String getcustomer() {
        return customer;
    }

    public void setcustomer(String customer) {
        this.customer = customer;
    }

    public String getdriver() {
        return driver;
    }

    public void setdriver(String driver) {
        this.driver = driver;
    }


    public String getlat() {
        return lat;
    }

    public void setlat(String lat) {
        this.lat = lat;
    }

    public String getlng() {
        return lng;
    }

    public void setlng(String lng) {
        this.lng = lng;
    }

    public String getpredictDistance() {
        return predictDistance;
    }

    public void setpredictDistance(String predictDistance) {
        this.predictDistance = predictDistance;
    }


    public String getrating() {
        return rating;
    }

    public void setrating(String rating) {
        this.rating = rating;
    }

    public String gettimestamp() {
        return timestamp;
    }

    public void settimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String gettriptime() {
        return triptime;
    }

    public void settriptime(String triptime) {
        this.triptime = triptime;
    }

    public String getimage() {
        return image;
    }

    public void setimage(String image) {
        this.image = image;
    }


    public String getjob() {
        return job;
    }

    public void setjob(String job) {
        this.job = job;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }


    public String getquote() {
        return quote;
    }

    public void setquote(String quote) {
        this.quote = quote;
    }

    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }


    public String getdriverFoundID() {
        return driverFoundID;
    }

    public void setdriverFoundID(String driverFoundID) {
        this.driverFoundID = driverFoundID;
    }

    public String getdescriptions() {
        return descriptions;
    }

    public void setdescriptions(String descriptions) {
        this.descriptions = descriptions;
    }


    public String getfield() {
        return field;
    }

    public void setfield(String field) {
        this.field = field;
    }


    public String getcustomerPaid() {
        return customerPaid;
    }

    public void setcustomerPaid(String customerPaid) {
        this.customerPaid = customerPaid;
    }

    public String getdriverPaidOut() {
        return driverPaidOut;
    }

    public void setdriverPaidOut(String driverPaidOut) {
        this.driverPaidOut = driverPaidOut;
    }

    public String getoperations() {
        return operations;
    }

    public void setoperations(String operations) {
        this.operations = operations;
    }


    public String getreviewBy() {
        return reviewBy;
    }

    public void setreviewBy(String reviewBy) {
        this.reviewBy = reviewBy;
    }

    public String getresidences() {
        return residences;
    }

    public void setresidences(String residences) {
        this.residences = residences;
    }

    public String getservice() {
        return service;
    }

    public void setservice(String service) {
        this.service = service;
    }

    public String getsetinformations() {
        return setinformations;
    }

    public void setsetinformations(String setinformations) {
        this.setinformations = setinformations;
    }

    public String getbaseprice() {
        return baseprice;
    }

    public void setbaseprice(String baseprice) {
        this.baseprice = baseprice;
    }


    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }


    public String getdesc() {
        return desc;
    }

    public void setdesc(String desc) {
        this.desc = desc;
    }


    public String gettid() {
        return tid;
    }

    public void settid(String tid) {
        this.tid = tid;
    }

    public String getcondition(String condition) {
        return condition;
    }
    public void settcondition(String condition) {
        this.condition = condition;
    }

    public String getcar(String car) {
        return car;
    }
    public void setcar(String car) {
        this.car = car;
    }

    public String getfollowersname(String followersname) {
        return followersname;
    }
    public void setfollowersname(String followersname) {
        this.followersname = followersname;
    }
    public String getuid(String uid) {
        return uid;
    }
    public void setuid(String uid) {
        this.uid = uid;
    }

}

