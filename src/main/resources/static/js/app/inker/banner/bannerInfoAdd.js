var validator;
var $bannerInfoAddForm = $("#bannerInfo-add-form");

$(function () {
    // validateRule();
    // createMenuTree();

    $("#bannerInfo-add .btn-save").click(function () {
        var name = $(this).attr("name");
        // getMenu();
        var validator = $bannerInfoAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                // var bannerInfo = $("#bannerInfo").val();
                $.post(ctx + "bannerInfo/addbannerInfo", $bannerInfoAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("bannerInfoTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "bannerInfo/updatebannerInfo",$bannerInfoAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("bannerInfoTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#bannerInfo-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#bannerInfo-add-button").attr("name", "save");
    $("#bannerInfo-add-modal-title").html('新增首页轮播');
    // $bannerInfoAddForm.resetForm();
    $bannerInfoAddForm.validate().resetForm();
    // $MB.resetJsTree("menuTree");
    //初始化图片
    $('#selectPictureFile')[0].src = "";
    $MB.closeAndRestModal("bannerInfo-add");
}

    function showPreview(source) {
        if(window.FileReader) {
            var file = source.files[0];
            if(window.FileReader) {
                var fr = new FileReader();
                fr.onloadend = function(e) {
                    document.getElementById("selectPictureFile").src = e.target.result;
                };
                fr.readAsDataURL(file);  //也是利用将图片作为url读出
            }
        }
        else {
            alert("Not supported by your browser!");
        }

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $bannerInfoAddForm.validate({
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