var validator;
var $propertyValueAddForm = $("#property-value-add-form");
$(function () {
    $("#property-value-add .btn-save").click(function () {
        var propertyValue = $("#property_value").val().trim();
        console.log("propertyValue: "+propertyValue)
        var name = $(this).attr("name");
        // getMenu();
        var validator = $propertyValueAddForm.validate();
        var flag = validator.form();
        if (flag) {
            var checkedId = getChecked();
            if (name === "save") {
                $.post(ctx + "property_value/add", {value:propertyValue,"propertyNameId":checkedId}, function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("propertyValueTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                var param = $.param({'propertyNameId':checkedId})+"&"+$propertyValueAddForm.serialize();
                $.post(ctx + "property_value/update", param, function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("propertyValueTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#property-value-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#property-value-add-button").attr("name", "save");
    $("#property-value-add-modal-title").html('新增属性');
    $MB.closeAndRestModal("property-value-add");
}

function getChecked(){
    var chk_value =[];
    var checkedPropertyNameId = $('input[name="checkbox_property_name"]:checked').val();
    return checkedPropertyNameId;
}
