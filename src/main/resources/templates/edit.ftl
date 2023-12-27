<#-- @ftlvariable name="article" type="com.example.models.Article" -->
 <#import "_layout.ftl" as layout />
 <@layout.header>
 <div>
 <h3>Edit Article</h3>
 <form action="/article/${article.id}/edit" method="post">
    <div>
    <label for="title">Title:
        <input type="text" id="title" name="title" value="${article.title}"/>
    </label>
    </div>
    <div>
    <label for="body">Body:
        <textarea id="body" name="body" rows="10" cols="120">${article.body}</textarea>
    </label>
    </div>
    <button id="submit" type="submit">Submit</button>
 </form>
 </div>
 </@layout.header>