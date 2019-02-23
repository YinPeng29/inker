$(function () {
    var settings = {
        url: ctx + "bannerInfo/bannerInfoList",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $(".bannerInfo-table-form").find("input[name='title']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'sha2',
            title: '图片(sha2)',
            formatter:function (value, row, index) {
                return "<img style='width: 50px;height: 50px;' src='/img/avatar/default.jpg' alt=''>"
            }
        }, {
            field: 'title',
            title: '描述'

        }, {
            field: 'to_url',
            title: '跳转地址'
        }, {
            field: 'banner_index',
            title: '播放序号',
        }, {
            field: 'create_date',
            title: '创建时间'
        }, {
            field: 'status',
            title: '是否开启',
            formatter:function (value, row, index) {
                if (value == 0){
                    return "开启";
                } else {
                    return "禁用";
                }
            }
        }, {
            field: 'id',
            title: '编辑',
            formatter:function(value,row,index){
                // onclick=\"allocationManager("+row.loanId +","+row.market_source+",'"+row.company_name+"')\"
                var operation='<a href="javascript:void(0);" title="启动" onclick="" >启用轮播</i></a>'
                    +'&#12288;<a href="javascript:void(0);" title="查看"  onclick="">编辑</a>'
                    +"&#12288;<a href='javascript:void(0);' title='更多'  data-target='#customerManagerModel' data-toggle='modal'  >更多</a>";
                return operation;
            }
        }]
    };

    $MB.initTable('bannerInfoTable', settings);
});

function search() {
    $MB.refreshTable('bannerInfoTable');
}

function refresh() {
    $(".bannerInfo-table-form")[0].reset();
    search();
}

function updatebannerInfo() {
    var selected = $("#bannerInfoTable").bootstrapTable('getSelections');
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
    $.post(ctx + "bannerInfo/getBannerInfoById", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#bannerInfo-add');
            $form.modal();
            var bannerInfo = r.msg;
            $("#bannerInfo-table-form").html('修改尺寸');
            $form.find("input[name='bannerInfo']").val(bannerInfo.size);
            $form.find("input[name='id']").val(bannerInfo.id);
            $("#bannerInof-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function deletebannerInfos() {
    var selected = $("#bannerInfoTable").bootstrapTable('getSelections');
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
        $.post(ctx + 'bannerInfo/deletebannerInfoByIds', {"sizeIds": ids}, function (r) {
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
    $.post(ctx + "bannerInfo/excel", $(".bannerInfo-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportRoleCsv() {
    $.post(ctx + "bannerInfo/csv", $(".bannerInfo-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}