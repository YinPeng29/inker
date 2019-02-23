$(function () {
    var settings = {
        url: ctx + "property_name/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                name: $(".property-name-table-form").find("input[name='propertyName']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '属性ID'
        }, {
            field: 'name',
            title: '属性名称'
        }, {
            field: 'create_time',
            title: '创建时间'
        },{
            field: 'modify_time',
            title: '修改时间'
        },{
            field: 'creator',
            title: '创建人'
        },{
            field: 'modifier',
            title: '修改人'
        }]
    };
    $MB.initTable('propertyNameTable', settings);
});

function search() {
    $MB.refreshTable('propertyNameTable');
}

function refresh() {
    $(".property-name-table-form")[0].reset();
    search();
}

function updatePropertyName() {
    var selected = $("#propertyNameTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的信息！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一条信息！');
        return;
    }
    var id = selected[0].id;
    selectedValueOfAll(id);
    $.post(ctx + "property_name/getById", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#property-name-add');
            $form.modal();
            var property_name = r.msg;
            $("#property-name-add-modal-title").html('修改属性');
            $form.find("input[name='name']").val(property_name.name);
            $form.find("input[name='id']").val(property_name.id);
            $("#property-name-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function addPropertyName() {
    // $.post(ctx + "property_value/selectAll", {}, function (r) {
    //     if (r.code === 0) {
    //         var data = r.msg;
    //         var div =document.getElementById("checkDiv");
    //         div.innerHTML="";
    //         var str = "";
    //         for(var i in data){
    //             str = str+
    //                 "<label class='checkbox-inline'"+">"
    //                     +"<input type='checkbox' name='checkbox_property_value' value='"+data[i].id+"'" +"/>"
    //                     +data[i].value
    //                     +"</label>";
    //         }
    //         div.innerHTML = str ;
    //     } else {
    //         $MB.n_danger(r.msg);
    //     }
    // });
}

function selectedValueOfAll(propertyNameId){
    // $.post(ctx + "property_name_value/getSelectedValue", {"propertyNameId":propertyNameId}, function (r) {
    //     if (r.code === 0) {
    //         var data = r.msg;
    //         var div =document.getElementById("checkDiv");
    //         div.innerHTML="";
    //         var str = "";
    //         for(var i in data){
    //             console.log()
    //             str = str +
    //                 "<label class='checkbox-inline'"+">"
    //                 +"<input type='checkbox' name='checkbox_property_value' value='" + data[i].id+"'";
    //                     if(data[i].id == data[i].creator){
    //                         str += "checked";
    //                     }
    //             str = str + "/>" +data[i].value +"</label>";
    //         }
    //         div.innerHTML = str ;
    //     } else {
    //         $MB.n_danger(r.msg);
    //     }
    // });
}

// function deletePropertyName() {
//     var selected = $("#propertyNameTable").bootstrapTable('getSelections');
//     var selected_length = selected.length;
//     if (!selected_length) {
//         $MB.n_warning('请勾选需要信息！');
//         return;
//     }
//     var ids = "";
//     for (var i = 0; i < selected_length; i++) {
//         ids += selected[i].id;
//         if (i !== (selected_length - 1)) ids += ",";
//     }
//
//     $MB.confirm({
//         text: "删除选中属性，将导致商品属性消失，造成不必要的麻烦，确定删除？",
//         confirmButtonText: "确定删除"
//     }, function () {
//         $.post(ctx + 'size/deleteSizeByIds', {"sizeIds": ids}, function (r) {
//             if (r.code === 0) {
//                 $MB.n_success(r.msg);
//                 refresh();
//             } else {
//                 $MB.n_danger(r.msg);
//             }
//         });
//     });
// }
//
// function exportRoleExcel() {
//     $.post(ctx + "role/excel", $(".property-name-table-form").serialize(), function (r) {
//         if (r.code === 0) {
//             window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
//         } else {
//             $MB.n_warning(r.msg);
//         }
//     });
// }
//
// function exportRoleCsv() {
//     $.post(ctx + "role/csv", $(".property-name-table-form").serialize(), function (r) {
//         if (r.code === 0) {
//             window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
//         } else {
//             $MB.n_warning(r.msg);
//         }
//     });
// }