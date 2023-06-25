const server = "http://192.168.1.6:8080/api/v1"
$(document).ready( function () {
    $('#storeTable').DataTable();
});

$(document).ready(function () {

    getStores();

    function dataConverter(formData,index){
        const {id,storeName,storeAddress} = formData
        return [index,storeName,storeAddress,`<button id="edit" data-id="${id}" title="Edit" class="btn btn-outline-primary rounded px-3 btn-sm"><i class="fas fa-pen"></i></button> <button id="delete"  data-id="${id}" title="Delete" class="btn btn-outline-danger rounded px-3 btn-sm"><i class="fas fa-trash"></i></button>`]
    }



    // function hit api and to get all stores and add them to the jquery datatable
    async function getStores() {
        $.ajax({
            url: server + '/store/stores',
            type: 'GET',
            success: function (data) {
                console.log(data); 
                // let myData = []
                $('#storeTable').DataTable().clear().draw();
                data.forEach( (store,index) => {
                    let reqData = dataConverter(store,index+1)
                    const oTable = $('#storeTable').dataTable();
                    oTable.fnAddData(reqData);
                });
                // $('#storeTable').DataTable().clear().draw();
                // $('#storeTable').DataTable().rows.add(myData).draw();
            },
            error: function (error) {
                console.log(error);
            }
        });
    }   


   


    // click event listner on "Add Store" button to open add form
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


    // addForm Submit
    $(document).on('submit', '#addForm', function (e) {
        // prevent default  
        e.preventDefault();
        console.log(e.target)
        // replace text of button with spinner
        $('#saveStore').html('<span class="spinner-border spinner-border-sm" style="margin: 0px 34.87px;" role="status" aria-hidden="true"></span>');

        //  get formdata
        var formData = new FormData(document.getElementById('addForm'));

        var json = JSON.stringify(Object.fromEntries(formData));

        console.log("JSON = " + json);

        // add store
        $.ajax({
            url: server + '/stores',
            type: 'POST',
            data: json,
            dataType: 'json',
            contentType: 'application/json',
            success: function (data) {
                // get all stores
                getStores();
                
                // replace spinner with text
                $('#saveStore').html('Add Store');
                
                // close modal
                $('#addModal').modal('hide');

                // clear form
                $('#addModal').find('form').trigger('reset');
            },
            error: function (error) {
                console.log(error);

                // replace spinner with text
                $('#saveStore').html('Add Store');
                
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
        // copy details of store from table row to modal
        $('#editModal').find('#sid').val(id);
        $('#editModal').find('#storeName').val($(this).closest('tr').find('td').eq(1).text());
        $('#editModal').find('#storeAddress').val($(this).closest('tr').find('td').eq(2).text());

        // open modal
        $('#editModal').modal('show');
    });

    // add on click event listner on element with id as "update"
    $(document).on('submit', '#editForm', function (e) {
        // prevent default  
        e.preventDefault();
        // replace text of button with spinner
        $('#update').html('<span class="spinner-border spinner-border-sm" style="margin: 0px 38.15px;" role="status" aria-hidden="true"></span>');

        var formData = new FormData(document.getElementById('addForm'));

        var json = JSON.stringify(Object.fromEntries(formData));

        console.log("JSON = " + json);

        // update store
        $.ajax({
            url: server + '/stores',
            type: 'PUT',
            data: json,
            contentType: 'application/json',
            success: function (data) {
                // get all stores
                getStores();

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

        // delete store
        $.ajax({
            url: server + '/stores/' + id,
            type: 'DELETE',
            success: function (data) {
                // get all stores
                getStores();
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
