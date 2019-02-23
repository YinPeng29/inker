var validator;
var $sizeAddForm = $("#size-add-form");

$(function () {
    // validateRule();
    // createMenuTree();

    $("#size-add .btn-save").click(function () {
        var name = $(this).attr("name");

        // getMenu();
        var validator = $sizeAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                var size = $("#size").val();
                $.post(ctx + "size/addSize", {"size":size.trim()}, function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("sizeTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {

                $.post(ctx + "size/updateSize",$sizeAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("sizeTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#size-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#size-add-button").attr("name", "save");
    $("#size-add-modal-title").html('新增尺寸');
    // validator.resetForm();
    // $MB.resetJsTree("menuTree");
    $MB.closeAndRestModal("size-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $sizeAddForm.validate({
        rules: {
            roleName: {
                required: true,
                minlength: 3,
                maxlength: 10,
                remote: {
                    url: "role/checkRoleName",
                    type: "get",
                    dataType: "json",
                    data: {
                        roleName: function () {
                            return $("input[name='roleName']").val().trim();
                        },
                        oldRoleName: function () {
                            return $("input[name='oldRoleName']").val().trim();
                        }
                    }
                }
            },
            remark: {
                maxlength: 50
            },
            menuId: {
                required: true
            }
        },
        messages: {
            roleName: {
                required: icon + "请输入角色名称",
                minlength: icon + "角色名称长度3到10个字符",
                remote: icon + "该角色名已经存在"
            },
            remark: icon + "角色描述不能超过50个字符",
            menuId: icon + "请选择相应菜单权限"
        }
    });
}

function createMenuTree() {
    $.post(ctx + "menu/menuButtonTree", {}, function (r) {
        if (r.code === 0) {
            var data = r.msg;
            $('#menuTree').jstree({
                "core": {
                    'data': data.children
                },
                "state": {
                    "disabled": true
                },
                "checkbox": {
                    "three_state": false
                },
                "plugins": ["wholerow", "checkbox"]
            });
        } else {
            $MB.n_danger(r.msg);
        }
    })

}

function getMenu() {
    var $menuTree = $('#menuTree');
    var ref = $menuTree.jstree(true);
    var menuIds = ref.get_checked();
    $menuTree.find(".jstree-undetermined").each(function (i, element) {
        menuIds.push($(element).closest('.jstree-node').attr("id"));
    });
    $("[name='menuId']").val(menuIds);
}