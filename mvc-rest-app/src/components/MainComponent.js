import React, { useState, useEffect } from 'react';
import axios from 'axios';

const MainComponent = () => {
    const [products, setProducts] = useState([]);
    const [formData, setFormData] = useState({
        name: '',
        price: '',
        category: ''
    });

    const API_URL = 'http://localhost:8888/step08-mvc-rest/api/products';

    // 1. 컴포넌트 로드시 전체 ProductDTO 정보를 출력
    const fetchProducts = async () => {
        try {
            const response = await axios.get(API_URL);
            setProducts(response.data);
        } catch (error) {
            console.error('Error fetching products:', error);
        }
    };

    useEffect(() => {
        fetchProducts();
    }, []);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({
            ...formData,
            [name]: value
        });
    };

    // 2. 전체 데이터 출력된 표 상단에 ProductDTO 입력해서 추가하는 폼 작성.
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post(API_URL, {
                ...formData,
                price: parseInt(formData.price)
            });
            setFormData({ name: '', price: '', category: '' }); // 폼 초기화
            fetchProducts(); // 목록 갱신
        } catch (error) {
            console.error('Error adding product:', error);
        }
    };

    return (
        <div style={{ padding: '20px' }}>
            <h2>Product Management</h2>

            {/* Product 입력 폼 */}
            <form onSubmit={handleSubmit} style={{ marginBottom: '20px', border: '1px solid #ccc', padding: '15px', borderRadius: '5px' }}>
                <div style={{ marginBottom: '10px' }}>
                    <label>Name: </label>
                    <input type="text" name="name" value={formData.name} onChange={handleChange} required />
                </div>
                <div style={{ marginBottom: '10px' }}>
                    <label>Price: </label>
                    <input type="number" name="price" value={formData.price} onChange={handleChange} required />
                </div>
                <div style={{ marginBottom: '10px' }}>
                    <label>Category: </label>
                    <input type="text" name="category" value={formData.category} onChange={handleChange} required />
                </div>
                <button type="submit">Add Product</button>
            </form>

            {/* Product 목록 테이블 */}
            <table border="1" style={{ width: '100%', borderCollapse: 'collapse' }}>
                <thead>
                    <tr style={{ backgroundColor: '#f2f2f2' }}>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                    </tr>
                </thead>
                <tbody>
                    {products.map(product => (
                        <tr key={product.id}>
                            <td>{product.id}</td>
                            <td>{product.name}</td>
                            <td>{product.price.toLocaleString()}</td>
                            <td>{product.category}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default MainComponent;
