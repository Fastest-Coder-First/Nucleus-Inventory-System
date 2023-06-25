        //  {
        //     "userName" : "test user",
        //     "password" : "test pass",
        //     "phoneNumber" : "test phone",
        //     "email" : "1@gmail.com",
        //     "address" :  {
        //         "addressLine" : "address 1",
        //         "city" : "city",
        //         "state" : "state",
        //         "country" : "country"
        //     }

function convertData(data) {

    return {
        id : 1,
        userName: data.userName,
        password: data.password,
        phoneNumber: data.phoneNumber,
        email: data.email,
        address : {
            addressLine: data.addressLine,
            city : data.city,
            state : data.state,
            country : data.country
        }
    }
}


$("#user-register").submit(function (e) {
    e.preventDefault();
    var formData = new FormData(e.target);
    let data = Object.fromEntries(formData);
    data = convertData(data)
    console.log(data)
    const { userName, email, password, phoneNumber } = data;
      console.log(JSON.stringify(data));
      $.ajax({
        type: "POST",
        url: "http://192.168.1.6:8080/api/v1/user/add-user",
        contentType: "application/json",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (data) {
          console.log("success " ,data);
          alert(data.message)
          // myModal.modal("show");
          // $(".modal-title").text("user register successfully");
          // $(".modal-body > p").text(data.message);
          const url = 'http://127.0.0.1:5501/products';
          window.location = url;    
        },
        error: function (data) {
          console.log("error ",data);
          // myModal.modal("show");
          alert(JSON.parse(data.responseText).message)
          // $(".modal-title").text("user register failed");
          // $(".modal-body > p").text(JSON.parse(data.responseText).message);
          // JSON.parse(data.responseText).errors.forEach((data) =>
          //   $(".modal-body > p").append(`<br> ${data}`)
          // );
        },
      });
  });



  
$("#user-login").submit(function (e) {
  e.preventDefault();
  var formData = new FormData(e.target);
  let data = Object.fromEntries(formData);
  console.log(JSON.stringify(data));
    $.ajax({
      type: "POST",
      url: "http://192.168.1.6:8080/api/v1/user/login",
      contentType: "application/json",
      data: JSON.stringify(data),
      dataType: "json",
      success: function (data) {
        console.log("success " ,data);
        alert(data.message)
        // myModal.modal("show");
        // $(".modal-title").text("user register successfully");
        // $(".modal-body > p").text(data.message);
        const url = 'http://127.0.0.1:5501/products/';
        window.location = url;    
      },
      error: function (data) {
        console.log("error ",data);
        // myModal.modal("show");
        alert(JSON.parse(data.responseText).message)
        // $(".modal-title").text("user register failed");
        // $(".modal-body > p").text(JSON.parse(data.responseText).message);
        // JSON.parse(data.responseText).errors.forEach((data) =>
        //   $(".modal-body > p").append(`<br> ${data}`)
        // );
      },
    });
});