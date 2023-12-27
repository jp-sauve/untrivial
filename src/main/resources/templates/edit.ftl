<#-- @ftlvariable name="article" type="com.example.models.Article" -->
 <#import "_layout.ftl" as layout />
 <@layout.header>
 <div>
 <h3>Edit Article</h3>
 <form action="/article/${article.id}" method="post">
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
    <button id="submit" type="submit" name="_action" value="update">Submit</button>
 </form>
     <div>
         <form action="/article/${article.id}" method="post">
             <p>
                 <input type="submit" name="_action" value="delete">
             </p>
         </form>
     </div>
 </div>
 </@layout.header>