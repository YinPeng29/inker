$(function () {
    var settings = {
        url: ctx + "property_value/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                value: $(".property-value-table-form").find("input[name='propertyValue']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '属性值ID'
        }, {
            field: 'value',
            title: '属性值'
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

    $MB.initTable('propertyValueTable', settings);
});

function search() {
    $MB.refreshTable('propertyValueTable');
}

function refresh() {
    $(".property-value-table-form")[0].reset();
    search();
}

function updatePropertyValue() {
    var selected = $("#propertyValueTable").bootstrapTable('getSelections');
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
    selectedNameOfAll(id);
    $.post(ctx + "property_value/getById", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#property-value-add');
            $form.modal();
            var property_value = r.msg;
            $("#property-value-add-modal-title").html('修改属性');
            $form.find("input[name='value']").val(property_value.value);
            $form.find("input[name='id']").val(property_value.id);
            $("#property-value-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}


function addPropertyValue() {
    $.post(ctx + "property_name/selectAll", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            var div =document.getElementById("pn_checkDiv");
            div.innerHTML="";
            var str = "";
            for(var i in data){
                str = str+
                    "<label class='checkbox-inline'"+">"
                    +"<input type='radio' name='checkbox_property_name' value='"+data[i].id+"'" +"/>"
                    +data[i].name
                    +"</label>";
            }
            div.innerHTML = str ;
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function selectedNameOfAll(propertyValueId){
    $.post(ctx + "property_name_value/getSelectedName", {"propertyValueId":propertyValueId}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            var div =document.getElementById("pn_checkDiv");
            div.innerHTML="";
            var str = "";
            for(var i in data){
                console.log()
                str = str +
                    "<label class='checkbox-inline'"+">"
                    +"<input type='radio' name='checkbox_property_name' value='" + data[i].id+"'";
                if(data[i].id == data[i].creator){
                    str += "checked";
                }
                str = str + "/>" +data[i].name +"</label>";
            }
            div.innerHTML = str ;
        } else {
            $MB.n_danger(r.msg);
        }
    });
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