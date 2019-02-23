var validator;
var $propertyNameAddForm = $("#property-name-add-form");
$(function () {
    $("#property-name-add .btn-save").click(function () {
        var propertyName = $("#property_name").val().trim();
        console.log("propertyName: "+propertyName)
        var name = $(this).attr("name");
        // getMenu();
        var validator = $propertyNameAddForm.validate();
        var flag = validator.form();
        if (flag) {
            // var checkedValue = JSON.stringify(getChecked());
            var checkedValue = getChecked();
            if (name === "save") {
                $.post(ctx + "property_name/add", {"name":propertyName,"propertyValueIds":checkedValue}, function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("propertyNameTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                var param = $.param({'propertyValueIds':checkedValue})+"&"+$propertyNameAddForm.serialize();
                $.post(ctx + "property_name/update", param , function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("propertyNameTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#property-name-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#property-name-add-button").attr("name", "save");
    $("#property-name-add-modal-title").html('新增属性');
    $MB.closeAndRestModal("property-name-add");
}

function getChecked(){
    var chk_value =[];
    $('input[name="checkbox_property_value"]:checked').each(function(){
        chk_value.push($(this).val());
    });
    return chk_value;
}

