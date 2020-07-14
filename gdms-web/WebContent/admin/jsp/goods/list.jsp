<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<base href="${ href }">
<title>后台管理</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<link rel="stylesheet" href="css/dialog-min.css">

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/dialog-min.js"></script>
<script src="js/pintuer.js"></script>
</head>
<body>
	<form method="post" action="" id="listform">
		<div class="panel admin-panel">
			<div class="panel-head">
				<strong class="icon-reorder"> 内容列表</strong> <a href=""
					style="float: right; display: none;">添加字段</a>
			</div>
			<div class="padding border-bottom">
				<ul class="search" style="padding-left: 10px;">
					<li><a class="button border-main icon-plus-square-o"
						href="add.html"> 添加内容</a></li>
					<li>搜索：</li>
					<li>首页 <select name="s_ishome" class="input"
						onchange="changesearch()"
						style="width: 60px; line-height: 17px; display: inline-block">
							<option value="">选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> &nbsp;&nbsp; 推荐 <select name="s_isvouch" class="input"
						onchange="changesearch()"
						style="width: 60px; line-height: 17px; display: inline-block">
							<option value="">选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> &nbsp;&nbsp; 置顶 <select name="s_istop" class="input"
						onchange="changesearch()"
						style="width: 60px; line-height: 17px; display: inline-block">
							<option value="">选择</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select>
					</li>
					<if condition="$iscid eq 1">
					<li><select name="cid" class="input"
						style="width: 200px; line-height: 17px;" onchange="changesearch()">
							<option value="">请选择分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
					</select></li>
					</if>
					<li><input type="text" placeholder="请输入搜索关键字" name="keywords"
						class="input"
						style="width: 250px; line-height: 17px; display: inline-block" />
						<a href="javascript:void(0)"
						class="button border-main icon-search" onclick="changesearch()">
							搜索</a></li>
				</ul>
			</div>
			<table class="table table-hover text-center">
				<tr>
					<th width="100" style="text-align: left; padding-left: 20px;">商品ID</th>
					<th width="10%">商品类别</th>
					<th>商品名称</th>
					<th>规格型号</th>
					<th>价格</th>
					<th>库存数量</th>
					<th width="10%">生产日期</th>
					<th width="10%">已推送</th>
					<th width="310">操作</th>
				</tr>

				<c:forEach items="${ model.data }" var="o">
					<tr>
						<td style="text-align: left; padding-left: 20px;"><input
							type="checkbox" name="id[]" value="" /> ${o.goodsid}</td>
						<td>${o.type.name}</td>
						<td width="10%">${o.name}</td>
						<td>${o.spec}</td>
						<td><font color="#00CC99">￥${o.price}</font></td>
						<td>${o.amount}</td>
						<td><f:formatDate value="${o.makedate}" /></td>
						<td><c:if test="${o.push}">
								已推送
							</c:if> 
							<c:if test="${!o.push}">
								<a  class="button border-main" href="javascript:void(0)"
									onclick="doPush(${o.goodsid},this)">
									<span class="icon-tint"></span>
									推送</a>
							</c:if></td>
						<td><div class="button-group">
								<a class="button border-main" href="add.html"><span
									class="icon-edit"></span> 修改</a> <a class="button border-red"
									href="javascript:void(0)" onclick="deldata(this) "> <span
									class="icon-trash-o"></span> 删除
								</a>
							</div></td>
					</tr>
				</c:forEach>






				<tr>
					<td style="text-align: left; padding: 19px 0; padding-left: 20px;"><input
						type="checkbox" id="checkall" /> 全选</td>
					<td colspan="8" style="text-align: left; padding-left: 20px;"><a
						href="javascript:void(0)" class="button border-red icon-trash-o"
						style="padding: 5px 15px;" onclick="DelSelect()"> 删除</a> <a
						href="javascript:void(0)"
						style="padding: 5px 15px; margin: 0 10px;"
						class="button border-blue icon-edit" onclick="sorts()"> 排序</a> 操作：
						<select name="ishome"
						style="padding: 5px 15px; border: 1px solid #ddd;"
						onchange="changeishome(this)">
							<option value="">首页</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> <select name="isvouch"
						style="padding: 5px 15px; border: 1px solid #ddd;"
						onchange="changeisvouch(this)">
							<option value="">推荐</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> <select name="istop"
						style="padding: 5px 15px; border: 1px solid #ddd;"
						onchange="changeistop(this)">
							<option value="">置顶</option>
							<option value="1">是</option>
							<option value="0">否</option>
					</select> &nbsp;&nbsp;&nbsp; 移动到： <select name="movecid"
						style="padding: 5px 15px; border: 1px solid #ddd;"
						onchange="changecate(this)">
							<option value="">请选择分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
							<option value="">产品分类</option>
					</select> <select name="copynum"
						style="padding: 5px 15px; border: 1px solid #ddd;"
						onchange="changecopy(this)">
							<option value="">请选择复制</option>
							<option value="5">复制5条</option>
							<option value="10">复制10条</option>
							<option value="15">复制15条</option>
							<option value="20">复制20条</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="9">
						<div class="pagelist">
							共${model.total }页
							<c:if test="${model.current == 1 }">
								<a>首页</a>
							</c:if>
							<c:if test="${model.current != 1 }">
								<a href="admin/goods/list.php">首页</a>
							</c:if>

							${model.nav }

							<c:if test="${model.current == model.total }">
								<a>尾页</a>
							</c:if>
							<c:if test="${model.current != model.total }">
								<a href="admin/goods/list.php?pn=${model.total}">尾页</a>
							</c:if>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript">
		function doPush(goodsid, a){
			$.ajax({
				url:"admin/goods/push.php",
				type:"POST",
				data:{"goodsid":goodsid},
				success:function(result){
					var json = eval(result);
					if (json.data.code==0) {
						$(a).parent().text("已推送");//将页面按钮变成已推送
					}
						qipao(json.data.message);
				}
			});
		}
	
	
		function deldata(t) {
			var $t = $(t);
			queren("你不会真的要删除此记录吧?不会把不会吧?", $t.parent().parent().parent());
		}

		//搜索
		function changesearch() {

		}

		//单个删除
		function del(id, mid, iscid) {
			if (confirm("您确定要删除吗?")) {

			}
		}

		//全选
		$("#checkall").click(function() {
			$("input[name='id[]']").each(function() {
				if (this.checked) {
					this.checked = false;
				} else {
					this.checked = true;
				}
			});
		})

		//批量删除
		function DelSelect() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var t = confirm("您确认要删除选中的内容吗？");
				if (t == false)
					return false;
				$("#listform").submit();
			} else {
				alert("请选择您要删除的内容!");
				return false;
			}
		}

		//批量排序
		function sorts() {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");
				return false;
			}
		}

		//批量首页显示
		function changeishome(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量推荐
		function changeisvouch(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量置顶
		function changeistop(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量移动
		function changecate(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {

				$("#listform").submit();
			} else {
				alert("请选择要操作的内容!");

				return false;
			}
		}

		//批量复制
		function changecopy(o) {
			var Checkbox = false;
			$("input[name='id[]']").each(function() {
				if (this.checked == true) {
					Checkbox = true;
				}
			});
			if (Checkbox) {
				var i = 0;
				$("input[name='id[]']").each(function() {
					if (this.checked == true) {
						i++;
					}
				});
				if (i > 1) {
					alert("只能选择一条信息!");
					$(o).find("option:first").prop("selected", "selected");
				} else {

					$("#listform").submit();
				}
			} else {
				alert("请选择要复制的内容!");
				$(o).find("option:first").prop("selected", "selected");
				return false;
			}
		}
	</script>
</body>
</html>