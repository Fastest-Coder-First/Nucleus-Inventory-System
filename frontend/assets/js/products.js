const server = "http://10.60.90.1:8080/api/v1"
$(document).ready( function () {
    $('#productTable').DataTable();
});

$(document).ready(function () {

    getProducts();

    function dataConverter(formData,index){
        const {productId,productName,category,productPrice,rating,productQuantity,description} = formData
        return [index,productName,category,productPrice,rating,productQuantity,description,`<button id="edit" data-id=${productId} title="Edit" class="btn btn-outline-primary rounded px-3 btn-sm"><i class="fas fa-pen"></i></button> <button id="delete"  data-id=${productId} title="Delete" class="btn btn-outline-danger rounded px-3 btn-sm"><i class="fas fa-trash"></i></button>`]
    }


    // function hit api and to get all products and add them to the jquery datatable
    async function getProducts() {
        $.ajax({
            url: server + '/products',
            type: 'GET',
            success: function (data) {

                console.log(data); 
                // let myData = []
                $('#productTable').DataTable().clear().draw();
                data.forEach( (product,index) => {
                    let reqData = dataConverter(product,index+1)
                    const oTable = $('#productTable').dataTable();
                    oTable.fnAddData(reqData);
                });
                // $('#productTable').DataTable().clear().draw();
                // $('#productTable').DataTable().rows.add(myData).draw();
            },
            error: function (error) {
                console.log(error);
            }
        });
    }   


   


    // add on click event listner on element with id as "add"
    $(document).on('click', '#add', function () {
        // open modal
        $('#addModal').modal('show');
        // hit api to get stores and append to select field with id "store_id"
        $.ajax({
            url: server + '/store/stores',
            type: 'GET',
            success: function (data) {
                console.log(data);
                data.forEach(store => {
                    $('#store_id').append(`<option value=${store.id}>${store.storeName}</option>`);
                });
            },
            error: function (error) {
                console.log(error);
            }
        });
    });


    // add on click event listner on element with id as "save"
    $(document).on('submit', '#addForm', function (e) {
        // prevent default  
        e.preventDefault();
        console.log(e.target)
        // replace text of button with spinner
        $('#saveProduct').html('<span class="spinner-border spinner-border-sm" style="margin: 0px 34.87px;" role="status" aria-hidden="true"></span>');

        //  get formdata
        var formData = new FormData(document.getElementById('addForm'));

        // add product
        $.ajax({
            url: server + '/products',
            type: 'POST',
            data: JSON.stringify(Object.fromEntries(formData)),
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                // get all products
                getProducts();
                
                // replace spinner with text
                $('#saveProduct').html('Add Product');
                
                // close modal
                $('#addModal').modal('hide');

                // clear form
                $('#addModal').find('form').trigger('reset');
            },
            error: function (error) {
                console.log(error);

                // replace spinner with text
                $('#saveProduct').html('Add Product');
                
                // close modal
                $('#addModal').modal('hide');
            
                // clear form
                $('#addModal').find('form').trigger('reset');
            }
        });

    });



    // add on click event listner on element with id as "edit"
    $(document).on('click', '#edit', function () {
        var id = $(this).attr('data-id');
        // copy details of product from table row to modal
        // $('#editModal').find('#').val(id);
        $('#editModal').find('#name').val($(this).closest('tr').find('td:eq(1)').text());
        $('#editModal').find('#category').val($(this).closest('tr').find('td:eq(2)').text());
        $('#editModal').find('#price').val($(this).closest('tr').find('td:eq(3)').text());
        $('#editModal').find('#rating').val($(this).closest('tr').find('td:eq(5)').text());
        $('#editModal').find('#quantity').val($(this).closest('tr').find('td:eq(4)').text());
        $('#editModal').find('#description').val($(this).closest('tr').find('td:eq(4)').text());

        // open modal
        $('#editModal').modal('show');
    });

    // add on click event listner on element with id as "update"
    $(document).on('submit', '#editForm', function (e) {
        // prevent default  
        e.preventDefault();
        // replace text of button with spinner
        $('#update').html('<span class="spinner-border spinner-border-sm" style="margin: 0px 38.15px;" role="status" aria-hidden="true"></span>');

        // get data from form

        // var id = $('#editModal').find('#pid').val();
        // var name = $('#editModal').find('#name').val();
        // var category = $('#editModal').find('#category').val();
        // var price = $('#editModal').find('#price').val();
        // var quantity = $('#editModal').find('#quantity').val();

        var formData = new FormData(document.getElementById('addForm'));

        // update product
        $.ajax({
            url: server + '/products',
            type: 'PUT',
            data: JSON.stringify(Object.fromEntries(formData)),
            contentType: 'application/json',
            success: function (data) {
                // get all products
                getProducts();

                // replace spinner with text
                $('#update').html('Save changes');

                // close modal
                setTimeout(()=>{
                    $('#editModal').modal('hide');
                },500);
            },
            error: function (error) {
                console.log(error);

                // replace spinner with text
                $('#update').html('Save changes');

                // close modal
                setTimeout(()=>{
                    $('#editModal').modal('hide');
                },500);
            }
        });

        // clear form
        $('#editModal').find('form').trigger('reset');
    });



    // add on click event listner on element with id as "delete"
    $(document).on('click', '#delete', function () {
        var $target = $(this);
        var id = $(this).attr('data-id');

        // Replace text of button with spinner
        $target.html('<span class="spinner-border spinner-border-sm" style="width: 17px; margin: 1px -2.4px; height: 17px; vertical-align: sub;" role="status" aria-hidden="true"></span>');

        // delete product
        $.ajax({
            url: server + '/products/' + id,
            type: 'DELETE',
            success: function (data) {
                // get all products
                getProducts();
                console.log(data)
                // replace spinner with text
                $target.html('<i class="fas fa-trash"></i>');
            },
            error: function (error) {
                console.log(error);
                // replace spinner with text
                $target.html('<i class="fas fa-trash"></i>');
            }
        });
    });
});

// hit api to get stores and append to select field with id "store_id"
$.ajax({
    url: server + '/store/stores',
    type: 'GET',
    success: function (data) {
        console.log(data);
        data.forEach(store => {
            $('#storeFilter').append(`<option value=${store.id}>${store.storeName}</option>`);
        });
    },
    error: function (error) {
        console.log(error);
    }
});