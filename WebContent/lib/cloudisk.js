// @Author CheneyJin ZB6115110
// @email cheneyjin@outlook.com

// user-space 页面jquery

// get当前目录的数据

var line = null;
var $tr = null;

function getNewLineAndTr(){
	line =$("table tbody").find("tr").length;
	$tr = $("table tbody tr:last");
};

function getIndex(){
	getNewLineAndTr();
	var index = 0;
	if(line>1){
		index=parseInt($tr.children().eq(0).html());
	}
	return index=index+1;
}

function doShare(){
	if($("#share_type").val()=='file'){
		$.ajax({
			url : "/Cloudisk/share/file",
			method : "get",
			data : {
				file_id : $("#share_id").val()
			},
			dataType : "json",
			success : function(data) {
				if(data!=1)
					alert("文件夹删除失败！");
				else
					alert("文件分享成功，可以右击文件名复制链接给好友啦，或者在分享栏中查看。");
			}
		});
	}else{
		alert("暂时只支持文件共享，文件夹共享coding中...");
	}
	clearShareModal();
}

function sendToDeleteModal(del_id,del_type,del_idx,obj){
	$("#del_id").val(del_id);
	$("#del_type").val(del_type);
	$("#del_idx").val(del_idx);
	$('#deleteModal').modal("show");
}

function clearDeleteModal(){
	$("#del_id").val("");
	$("#del_type").val("");
	$("#del_idx").val("");
}

function sendToShareModal(share_id,share_type){
	$("#share_id").val(share_id);
	$("#share_type").val(share_type);
	$('#shareModal').modal("show");

}

function clearShareModal(){
	$("#share_id").val("");
	$("#share_type").val("");
}

function doDelete(){
	if($("#del_type").val()=='folder'){
		$.ajax({
			url : "/Cloudisk/folder/deleteFolder",
			method : "post",
			data : {
				folder_id : $("#del_id").val()
			},
			dataType : "json",
			success : function(data) {
				if(data!=1)
					alert("文件夹删除失败！");
			}
		});
	}else{
		$.ajax({
			url : "/Cloudisk/user-file/deleteFile",
			method : "post",
			data : {
				file_id : $("#del_id").val()
			},
			dataType : "json",
			success : function(data) {
				if(data!=1)
					alert("文件删除失败！");
			}
		});
	}
	removeTr($("#del_idx").val());
	clearDeleteModal();
}

function removeTr(id){
	$("tr[id="+id+"]").remove();
}

// 添加文件夹相关表格行
function addFoldersTr(value){
	$.ajax({
		url : "/Cloudisk/folder/chFolders",
		method : "get",
		data : {
			folder_id : value
		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, value) {
				getNewLineAndTr();
				$tr.after("<tr id="+getIndex()+">" +
						"<td>"+getIndex()+"</td>"+
						"<td><a href=\"javascript:direct("+value.id+")\">"+value.folderName+"</a></td>"+
						"<td>文件夹</td>"+
						"<td>-</td>"+
						"<td>"+value.creatime+"</td>"+
						"<td><a href=\"javascript:sendToShareModal("+value.id+",'folder');\">" +
						"<i class=\"icon-share-alt\"></i></a></td>"+
						"<td><a href=\"javascript:sendToDeleteModal("+value.id+",'folder',"+getIndex()+")\"> " +
						"<i class=\"icon-remove\"\"></i></a></td>"+
						"</tr>");
			});
		}
	});
}


function addFilesTr(value){
	$.ajax({
		url : "/Cloudisk/folder/chFiles",
		method : "get",
		data : {
			folder_id : value
		},
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, value) {
				getNewLineAndTr();
				$tr.after("<tr id="+getIndex()+">" +
						"<td>"+getIndex()+"</td>"+
						"<td><a href=\"/Cloudisk/user-file/downloadFile?file_id="+value.id+"\">"+value.fileName+"</a></td>"+
						"<td>"+value.fileType+"</td>"+
						"<td>"+value.fileSize+"</td>"+
						"<td>"+value.uploadTime+"</td>"+
						"<td><a href=\"javascript:sendToShareModal("+value.id+",'file');\">" +
						"<i class=\"icon-share-alt\"></i></a></td>"+
						"<td><a href=\"javascript:sendToDeleteModal("+value.id+",'file',"+getIndex()+")\">" +
						"<i class=\"icon-remove\"></i></a></td>"+
						"</tr>");
			});
		}
	});
}

function createFolderAndAdd2Tr(){
	///Cloudisk/folder/create
	var folder_name = $("#create_folder_name").val();	
	$.ajax({
		url : "/Cloudisk/folder/create",
		method : "get",
		data : {
			folder_name:folder_name
		},
		dataType : "json",
		success : function(data) {
			if(data!=0){
				getNewLineAndTr();
				$tr.after("<tr id="+getIndex()+">" +
						"<td>"+getIndex()+"</td>"+
						"<td><a href=\"javascript:direct("+data+");\">"+folder_name+"</a></td>"+
						"<td>文件夹</td>"+
						"<td>-</td>"+
						"<td>当前</td>"+
						"<td><a href=\"javascript:sendToShareModal("+data.id+",'folder');\">" +
						"<i class=\"icon-share-alt\"></i></a></td>"+
						"<td><a href=\"javascript:sendToDeleteModal("+data+",'folder',"+getIndex()+")\">" +
								"<i class=\"icon-remove\"></i></a></td>"+
						"</tr>");
			}else{
				alert("创建失败！");
			}
		}
	});
};


 function uploadFileAndAdd2Tr() {
     var elementIds=["file"]; //flag为id、name属性名
     $.ajaxFileUpload({
         url: '/Cloudisk/user-file/uploadFile', 
         type: 'post',
         secureuri: false,
         fileElementId: 'file', // 上传文件的id、name属性名
         dataType: 'json',
         elementIds: elementIds, //传递参数到服务器
         success: function(data){
        	 // ajaxupload 返回值data被解析为json时候会出现bug
        	 // 之运行error，修改ajaxupload源码
        	 // eval("data = " + data.substring(59,data.length-6));
        	 // @AUTHOR CheneyJin ZB6115110
        	 getNewLineAndTr();
        	 $tr.after("<tr id="+getIndex()+">" +
        				"<td>"+getIndex()+"</td>"+
        				"<td><a href=\"/Cloudisk/user-file/downloadFile?file_id="+data.id+"\">"+data.fileName+"</a></td>"+
        				"<td>"+data.fileType+"</td>"+
        				"<td>"+data.fileSize+"</td>"+
        				"<td>当前</td>"+
        				"<td><a href=\"javascript:sendToShareModal("+data.id+",'file');\">" +
        				"<i class=\"icon-share-alt\"></i></a></td>"+
        				"<td><a href=\"javascript:sendToDeleteModal("+data.id+",'file',"+getIndex()+")\">" +
        				"<i class=\"icon-remove\"></i></a></td>"+
        				"</tr>");
         },
         error: function(data){
        	alert("上传失败!");         	
         }
     });
 	$("#file").empty();
 }

// 清空表格，并创建用于标记的tr空标签
function emptyTable(){
	$("table tbody").empty();
	var tr ="<tr></tr>"
	$("table").append(tr);
}

function changeTitleDirect(value){
	$("#folder-direct").text(value);
	$("#folder-direct-title").text(value);
}

function loadData(value) {
	addFoldersTr(value);
	addFilesTr(value);
};

//跳转文件夹
function direct(value){
	emptyTable();
	$.ajax({
		url : "/Cloudisk/folder/direct",
		method : "get",
		data : {
			folder_id:value
		},
		dataType : "json",
		success : function(data) {
			if(data!=0){
				changeTitleDirect(data.folderName);
				loadData(value);
			}else{
				alert("目录打开失败！");
			}
		}
	});
}

function gotoShare(value){
	emptyTable();
	$.ajax({
		url : "/Cloudisk/share/userShareList",
		method : "get",
		data : {
			user_id:value
		},
		dataType : "json",
		success : function(data) {
			if(data!=""){
				changeTitleShare();
				loadFilesInShare(data);
				
			}else{
				//alert("分享文件显示失败！");
			}
		}
	});
}
function loadFilesInShare(data){
	$.each(data, function(i, value) {
		getNewLineAndTr();
		$tr.after("<tr id="+getIndex()+">" +
				"<td>"+getIndex()+"</td>"+
				"<td><a href=\"/Cloudisk/user-file/downloadFile?file_id="+value.id+"\">"+value.fileName+"</a></td>"+
				"<td>"+value.fileType+"</td>"+
				"<td>"+value.fileSize+"</td>"+
				"<td>"+value.uploadTime+"</td>"+
				"<td><a href=\"/Cloudisk/user-file/downloadFile?file_id="+value.id+"\">" +
				"<i class=\"icon-download\"></i></a></td>"+
				"<td><a href=\"javascript:sendToCancelShareModal("+value.id+",'file',"+getIndex()+")\">" +
				"<i class=\"icon-remove\"></i></a></td>"+
				"</tr>");
	});
	
}

function changeTitleShare(){
	$("#folder-direct").text("共享文件");
	$("#folder-direct-title").text("");
}

function sendToCancelShareModal(cancelShare_id,cancelShare_type,cancelShare_idx){
	$("#cancelShare_id").val(cancelShare_id);
	$("#cancelShare_type").val(cancelShare_type);
	$("#cancelShare_idx").val(cancelShare_idx);
	$('#cancelShareModal').modal("show");
}

function clearCancelShareModal(){
	$("#cancelShare_id").val("");
	$("#cancelShare_type").val("");
	$("#cancelShare_idx").val("");
}

function doCancelShare(){
	console.log($("#cancelShare_id").val());
	if($("#cancelShare_type").val()=='file'){
		$.ajax({
			url : "/Cloudisk/share/cancelShareFile",
			method : "post",
			data : {
				file_id : $("#cancelShare_id").val()
			},
			dataType : "json",
			success : function(data) {
				if(data!=1)
					alert("取消文件分享出错！");
			}
		});
	}else{
		alert("取消分享出錯！部分功能建設中...");
	}
	removeTr($("#cancelShare_idx").val());
	clearCancelShareModal();
}

$('input[type="file"]').prettyFile();
