import { render, screen } from '@testing-library/react';
import App from './App';

test('renders Product Management header', () => {
  render(<App />);
  const headerElement = screen.getByText(/Product Management/i);
  expect(headerElement).toBeInTheDocument();
});
