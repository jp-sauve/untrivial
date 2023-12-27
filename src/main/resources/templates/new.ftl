<<#-- @ftlvariable name="articles" type="kotlin.collections.List<com.example.models.Article>" -->
 <#import "_layout.ftl" as layout />
 <@layout.header>
 <div>
 <h3>Create Article</h3>
 <form action="/article" method="post">
    <div>
    <label for="title">Title:
        <input type="text" id="title" name="title"/>
    </label>
    </div>
    <div>
    <label for="body">Body:
        <textarea id="body" name="body" rows="10" cols="120"></textarea>
    </label>
    </div>
    <button id="submit" type="submit">Submit</button>
 </form>
 </div>
 </@layout.header>