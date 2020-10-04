package com.simcoder.bimbo.Model;


public class Products {

    private String desc, image, title, name, phone, discount, pid, price, quantity, tid;

    private String address, amount, city, date, state, distance, mode, number, shippingcost, time;

    private String count, uid;
    private String password;
    private String coverimage, g, one, zero, customerId, customerRideId, destination, destinationLat, destinationLng;
    private String driverFoundID,  predictDistance, lat, lng, rating, triptime;
    private String job, quote, role;

    private String car, descriptions;

    private String field,  customerPaid, driverPaidOut;
    private String timestamp, operations, reviewBy, residences, service, setinformations;
    private String baseprice; String traderimage; String tradername;


    private  String commentkey;
    private String comment;



    String likeid;

    String subject;

    String photoid;
    String orderkey;
    String reply;
    String replyid;



    String pname;
    String pimage;
    String categoryid;
    String reviewid;
    String categoryname;

    String size;






    public Products() {
    }



    public Products( String pid,  String tid, String quantity, String price,String desc, String discount, String name, String image, String tradername, String traderimage,String size

                     ) {this.desc = desc;
        this.image = image;



        this.name = name;
        this.tid = tid;
        this.discount = discount;
        this.pid = pid;
        this.price = price;
        this.quantity = quantity;
        this.tradername = tradername;
        this.traderimage = traderimage;
        this.size = size;
    }
    public Products(
            String desc, String image, String title, String name, String phone, String discount, String pid, String price, String quantity, String tid,

            String address, String amount, String city, String date, String state, String distance, String mode, String number, String shippingcost, String time,

            String count,  String uid, String password, String coverimage, String g, String one, String zero, String customerId, String customerRideId, String destination, String destinationLat, String destinationLng,
            String driverFoundID,   String predictDistance, String lat, String lng, String rating, String triptime,
            String job, String quote, String role, String car, String descriptions, String field,  String customerPaid, String driverPaidOut,
            String timestamp, String operations, String reviewBy, String residences, String service, String setinformations, String baseprice, String tradername, String traderimage, String subject,
            String orderkey,String reply,String replyid,String pname,String pimage,String categoryid,String reviewid, String categoryname,String photoid,
             String size, String likeid



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
        this.customerPaid = customerPaid;
        this.driverPaidOut = driverPaidOut;
        this.timestamp = timestamp;
        this.operations = operations;
        this.reviewBy = reviewBy;
        this.residences = residences;
        this.service = service;
        this.setinformations = setinformations;
        this.baseprice = baseprice;

        this.traderimage = traderimage;
        this.tradername= tradername;

        this.orderkey = orderkey;
        this.reply = reply;
        this.replyid = replyid;
        this.pname = pname;
        this.pimage = pimage;
        this.categoryid = categoryid;
        this.reviewid =reviewid;
        this.categoryname =categoryname;
        this.photoid= photoid;
        this.size =size;
        this.likeid = likeid;
        this.subject = subject;



    }



    public Products(String comment, String uid,  String date, String time, String tid, String name, String number, String subject, String likeid, String pid, String commentkey, String photoid, String orderkey,
                   String reply,String replyid,String traderimage,String tradername,String pname,String pimage,String image
    ) {
        this.comment = comment;
        this.uid = uid;

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



    public Products( String pname,String pimage,String desc, String price, String  pid, String date, String time, String tid,String  tradername, String traderimage)
     {



        this.pname = pname;
        this.pimage = pimage;
        this.desc = desc;
        this.price = price;
        this.pid = pid;
        this.date = date;
        this.time = time;
        this.tid= tid;
        this.tradername = tradername;
        this.traderimage = traderimage;


    }

    public Products(String categoryname, String date, String desc, String discount, String time, String pid, String pimage, String pname, String price, String image, String name, String size, String tradername, String traderimage, String tid) {
         this.categoryname = categoryname;
         this.date = date;
         this.desc = desc;
         this.discount = discount;
         this.time = time;
         this.pid = pid;
         this.pimage = pimage;
         this.pname = pname;
         this.price = price;
         this.image = image;
         this.name = name;
         this.size =size;
         this.tradername = tradername;
         this.traderimage = traderimage;
         this.tid = tid;



    }

    public Products(String categoryname, String date, String desc, String discount, String time, String pid, String pimage, String pname, String price, String image, String name, String size, String tradername, String traderimage, String tid, String number) {

        this.categoryname = categoryname;
        this.date = date;
        this.desc = desc;
        this.discount = discount;
        this.time = time;
        this.pid = pid;
        this.pimage = pimage;
        this.pname = pname;
        this.price = price;
        this.image = image;
        this.name = name;
        this.size =size;
        this.tradername = tradername;
        this.traderimage = traderimage;
        this.tid = tid;
        this.number = number;


    }


    public void setpimage(String pimage) {
        this.pimage = pimage;
    }


    public String getcategoryname() {
        return categoryname;
    }



    public void setcategoryname(String categoryname) {
        this.categoryname = categoryname;
    }


    public String getsize() {
        return size;
    }
    public void setsize(String size) {
        this.size = size;
    }



    public String getreviewid() {
        return reviewid;
    }
    public void setreviewid(String reviewid) {
        this.reviewid = reviewid;
    }




    public String getcategoryid() {
        return categoryid;
    }

    public void setcategoryid(String categoryid) {
        this.categoryid = categoryid;
    }


    public String getpimage() {
        return pimage;
    }






    public String gettradername() {
        return tradername;
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



    public void setreplyid(String replyid) {
        this.replyid = replyid;
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
    public void setuid(String uid) {
        this.uid = uid;
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


    public String getcar(String car) {
        return car;
    }
    public void setcar(String car) {
        this.car = car;
    }

   public String getuid(String uid) {
        return uid;
    }


  public String setTrader(String trader) {

        return trader;
    }
    public  String SetAnImage(String image){
        return  image;
    }
  }

