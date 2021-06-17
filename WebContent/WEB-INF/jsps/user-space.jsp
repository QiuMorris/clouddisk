<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Cloudisk 私有云</title>
  </head>

  <body class=""> 
    
    <div class="navbar">
        <div class="navbar-inner">
                <ul class="nav pull-right">
                    
                    <li><a href="#" class="hidden-phone visible-tablet visible-desktop" role="button">设置</a></li>
                    <li id="fat-menu" class="dropdown">
                        <a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="icon-user"></i> ${USER_NAME}
                            <i class="icon-caret-down"></i>
                        </a>

                        <ul class="dropdown-menu">
                            <li><a tabindex="-1" href="#">我的信息</a></li>
                            <li class="divider"></li>
                            <li><a tabindex="-1" class="visible-phone" href="#">设置</a></li>
                            <li class="divider visible-phone"></li>
                            <li><a tabindex="-1" href="/Cloudisk/login/out">登出</a></li>
                        </ul>
                    </li>
                    
                </ul>
                <a class="brand" href="/Cloudisk/user-space"><span class="first">Cloudisk</span> <span class="second">私有云</span></a>
        </div>
    </div>
    


    
    <div class="sidebar-nav">
        <a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i class="icon-dashboard"></i>菜单栏</a>
        <ul id="dashboard-menu" class="nav nav-list collapse in">
            <li class="active"><a href="/Cloudisk/user-space">主页</a></li>
            <li><a href="javascript:gotoShare(${USER_ID});">分享</a></li>
            
        </ul>

        <a href="#" class="nav-header" ><i class="icon-question-sign"></i>帮助</a>
        <a href="#" class="nav-header" ><i class="icon-comment"></i>问答</a>
    </div>
    

    
    <div class="content">
        
        <div class="header">
            
			<h1 id="folder-direct-title" class="page-title">${DIRECT}</h1>
        </div>
        
        <ul class="breadcrumb">
            <li><a id="folder-direct" href="javascript:direct(${DIRECT})">${DIRECT}</a> <span class="divider">/</span></li>
            <!-- <li class="active">Users</li> -->
        </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    
				<div class="btn-toolbar">
					<!-- <form id="upload-file-form" action="/Cloudisk/user-file/uploadFile" enctype="multipart/form-data" method="post"
						onsubmit="return uploadFileAndAdd2Tr();">  -->
				    	<br>
					    <input type="file" name="file" id="file" />
					    <button  onclick="uploadFileAndAdd2Tr()" class="btn">导入文件</button>
				    <!-- </form>  -->
				   	<a class="btn btn-primary" href="#createFolderModal" role="button" data-toggle="modal">
				    <i class="icon-plus"></i> 新建文件夹</a>
				    <!-- <button class="btn">Export</button>  -->
				  <div class="btn-group">
				  </div>
				</div>
				<div class="well">
				    <table id="folder-content" class="table">
				      <thead>
				        <tr>
				          <th>#</th>
				          <th>名称</th>
				          <th>格式</th>
				          <th>大小</th>
				          <th>日期</th>
				          <th> </th>				          				          
				          <th> </th>
				          <th style="width: 26px;"></th>
				        </tr>
				      </thead>
				      <tbody>
				      	<tr></tr>
				      </tbody>
				    </table>
				</div>

				<div class="modal small hide fade" id="createFolderModal" tabindex="-1" role="dialog" aria-labelledby="createFolderModalLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
				        <h3 id="createFolderModalLabel">创建文件夹</h3>
				    </div>
				    <div class="modal-body">
				        <p class="error-text">文件夹名称:&nbsp; <input type="text" class="form-control" id="create_folder_name" placeholder="请输入名称" style="margin-top:6px"/></p>
				    </div>
				    <div class="modal-footer">
				        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
				        <button class="btn btn-danger" id="createFolderBtn" data-dismiss="modal" type="submit" onclick="createFolderAndAdd2Tr()">创建</button>
				    </div>
				</div>

				<div class="modal small hide fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
				        <h3 id="deleteModalLabel">删除确认</h3>
				    </div>
				    <div class="modal-body">
				        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定要删除这个文件/文件夹吗?</p>
				    </div>
				    	<input type="text" id="del_id" value="" style="display:none;"/>
				  		<input type="text" id="del_type" value="" style="display:none;"/>
				    	<input type="text" id="del_idx" value="" style="display:none;"/>

				    <div class="modal-footer">
				        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
				        <button class="btn btn-danger" data-dismiss="modal"onclick="doDelete()">删除</button>
				    </div>
				</div>
				
				<div class="modal small hide fade" id="shareModal" tabindex="-1" role="dialog" aria-labelledby="shareModalLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
				        <h3 id="shareModalLabel">分享确认</h3>
				    </div>
				    <div class="modal-body">
				        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定要分享这个文件/文件夹吗?</p>
				    </div>
				    	<input type="text" id="share_id" value="" style="display:none;"/>
				  		<input type="text" id="share_type" value="" style="display:none;"/>
				    <div class="modal-footer">
				        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
				        <button class="btn btn-danger" data-dismiss="modal" onclick="doShare()">分享</button>
				    </div>
				</div>
				
				<div class="modal small hide fade" id="cancelShareModal" tabindex="-1" role="dialog" aria-labelledby="cancelShareModalLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
				        <h3 id="cancelShareModalLabel">取消分享</h3>
				    </div>
				    <div class="modal-body">
				        <p class="error-text"><i class="icon-warning-sign modal-icon"></i>你确定要取消分享这个文件吗?</p>
				    </div>
				    	<input type="text" id="cancelShare_id" value="" style="display:none;"/>
				  		<input type="text" id="cancelShare_type" value="" style="display:none;"/>
				  		<input type="text" id="cancelShare_idx" value="" style="display:none;"/>
				    <div class="modal-footer">
				        <button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
				        <button class="btn btn-danger" data-dismiss="modal" onclick="doCancelShare()">取消分享</button>
				    </div>
				</div>
				
                    
	            <footer>
	                <hr>
	            	<p>&copy; 2016 <a href="#" title="Cloudisk" target="_blank">Cloudisk</a></p>
	           	</footer>
                    
            </div>
        </div>
    </div>
    <script type="text/javascript" src="/Cloudisk/lib/cloudisk.js"></script>
    
    <script>
	    $(document).ready(function() {
	    	direct(${DIRECT});
	    });
    </script>
  </body>
</html>


