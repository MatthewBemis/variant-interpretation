import { useEffect, useState } from "react";

interface HelloResponse {
  timestamp: string;
}

function App() {
  const [timestamp, setTimestamp] = useState<string | null>(null);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch("/api/hello")
      .then((res) => {
        if (!res.ok) {
          throw new Error(`Request failed with status ${res.status}`);
        }
        return res.json() as Promise<HelloResponse>;
      })
      .then((data) => setTimestamp(data.timestamp))
      .catch((err: Error) => setError(err.message));
  }, []);

  if (error) {
    return <p>Failed to reach backend: {error}</p>;
  }

  if (!timestamp) {
    return <p>Loading...</p>;
  }

  return (
    <p>
      Hello world, it's {timestamp}
    </p>
  );
}

export default App;
