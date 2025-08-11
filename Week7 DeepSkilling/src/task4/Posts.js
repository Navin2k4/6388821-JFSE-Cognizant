import React from "react";

class Posts extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
    };
  }

  loadPosts() {
    fetch("https://jsonplaceholder.typicode.com/posts")
      .then((response) => response.json())
      .then((data) => this.setState({ posts: data }))
      .catch((error) => console.error("Error loading posts:", error));
  }

  componentDidMount() {
    this.loadPosts();
  }

  render() {
    const { posts } = this.state;
    return (
      <div>
        <h1 className="text-2xl font-bold text-center my-4">Posts</h1>
        {posts.length > 0 ? (
          <ul className="gap-3 text-xl">
            {posts.map((post) => (
              <li key={post.id}>{post.title}</li>
            ))}
          </ul>
        ) : (
          <p>Loading posts...</p>
        )}
      </div>
    );
  }

  componentDidCatch(error, errorInfo) {
    console.error("Error loading posts:", error, errorInfo);
  }
}

export default Posts;
