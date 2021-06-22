<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>

<!DOCTYPE html>
<html>

<body>

    <div id="visual">
        <div class="content-container"></div>
    </div>
    <!-- --------------------------- <body> --------------------------------------- -->
    <div id="body">
        <div class="content-container clearfix">

            <main>
                <h2 class="main title">공지사항 등록</h2>

                <form method="post" action="reg" enctype="multipart/form-data">
                    <div class="margin-top first">
                        <h3 class="hidden">공지사항 입력</h3>
                        <table class="table">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td class="text-align-left text-indent text-strong text-orange" colspan="3">
                                        <input type="text" name="title" />
                                    </td>
                                </tr>
                                <tr>
                                    <th>첨부파일1</th>
                                    <td colspan="3" class="text-align-left text-indent"><input type="file"
                                            name="file" /> </td>
                                </tr>
                                <tr>
                                    <th>첨부파일2</th>
                                    <td colspan="3" class="text-align-left text-indent"><input type="file"
                                            name="file" /> </td>
                                </tr>
                                <tr class="content">
                                    <td colspan="4"><textarea class="content" name="content"></textarea></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="margin-top text-align-center">
                        <input class="btn-text btn-default" type="submit" value="등록" />
                        <a class="btn-text btn-cancel" href="list">취소</a>
                    </div>
                </form>

            </main>
        </div>
    </div>
</body>

</html>