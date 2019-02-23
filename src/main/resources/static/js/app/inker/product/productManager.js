$(function () {

    //动态添加类别
    $.post(ctx + "category/getAllList", function (r) {
        var categoryList = r.rows;
        for(var i=0; i<categoryList.length;i++){ //循环添加多个值
            var category = categoryList[i];
            $("#category").append("<option value='"+category.id+"'>"+category.name+"</option>");
        }
    });

    var settings = {
        url: ctx + "product2/productList",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                name: $(".category-table-form").find("input[name='name']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'productId',
            visible: false,
        } ,{

            field: 'sha2',
            title: '商品图片',
            formatter : function (value, row, index) {
                return "<img style='width: 50px;height: 50px;' src='/img/avatar/default.jpg' alt=''>"
            }
        }, {
            field: 'productName',
            title: '商品标题'
        }, {
            field: 'price',
            title: '价格'
        }, {
            field: 'allStock',
            title: '总库存'
        }, {
            field: 'create_time',
            title: '创建时间'
        }, {
            field: 'status',
            title: '发布状态',
            formatter : function (value, row, index) {
                if (value == 1){
                    return "已发布";
                }else{

                    return "未发布"
                }
            }
        }, {
            field: '',
            title: '操作',
            formatter:function(value,row,index){
                // onclick=\"allocationManager("+row.loanId +","+row.market_source+",'"+row.company_name+"')\"
                var operation='<a href="javascript:void(0);" title="查看"  onclick="">编辑商品</a>'
                    +'&#12288;<a href="javascript:void(0);" title="编辑" onclick="" >编辑描述</i></a>'
                    +"&#12288;<a href='javascript:void(0);' title='更多'  data-target='#customerManagerModel' data-toggle='modal'  >更多</a>";
                return operation;
            }

        }]
    };
    $MB.initTable('categoryTable', settings);


});

function search() {
    $MB.refreshTable('categoryTable');
}

function refresh() {
    $(".category-table-form")[0].reset();
    search();
}

function updatecategory() {
    var selected = $("#categoryTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的类别！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个类别！');
        return;
    }
    var id = selected[0].id;
    $.post(ctx + "category/getCategoryById", {"id": id}, function (r) {
        if (r.code === 0) {
            var $form = $('#category-add');
            $form.modal();
            var category = r.msg;
            $("#category-add-modal-title").html('修改类别');
            $form.find("input[name='name']").val(category.name);
            $form.find("input[name='id']").val(category.id);
            $("#category-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}

function deletecategorys() {
    var selected = $("#categoryTable").bootstrapTable('getSelections');
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
        $.post(ctx + 'category/deletecategoryByIds', {"categoryIds": ids}, function (r) {
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
    $.post(ctx + "category/excel", $(".category-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportRoleCsv() {
    $.post(ctx + "category/csv", $(".category-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}