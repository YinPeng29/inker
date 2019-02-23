$(function () {
    var settings = {
        url: ctx + "size/getList",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                size: $(".size-table-form").find("input[name='size']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            title: '尺码id'
        }, {
            field: 'size',
            title: '尺码'
        }, {
            field: 'create_time',
            title: '创建时间'
        }]
    };

    $MB.initTable('sizeTable', settings);
});

function search() {
    $MB.refreshTable('sizeTable');
}

function refresh() {
    $(".size-table-form")[0].reset();
    search();
}

function updateSize() {
    var selected = $("#sizeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的尺寸！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个尺寸！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "size/getSizeById", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#size-add');
            $form.modal();
            var size = r.msg;
            $("#size-add-modal-title").html('修改尺寸');
            $form.find("input[name='size']").val(size.size);
            $form.find("input[name='id']").val(size.id);
            $("#size-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function deleteSizes() {
    var selected = $("#sizeTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的尺码！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "删除选中尺寸将导致该尺寸对应衣服失去相应的尺寸属性，确定删除？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'size/deleteSizeByIds', {"sizeIds": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportRoleExcel() {
    $.post(ctx + "size/excel", $(".size-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportRoleCsv() {
    $.post(ctx + "size/csv", $(".size-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}