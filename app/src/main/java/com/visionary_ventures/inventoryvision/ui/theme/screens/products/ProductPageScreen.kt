package com.visionary_ventures.inventoryvision.ui.theme.screens.products

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useState, useMemo, useEffect, useCallback } from 'react';
import { useSearchParams, useRouter } from 'next/navigation';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Select, SelectContent, SelectGroup, SelectItem, SelectLabel, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogDescription } from '@/components/ui/dialog';
import ProductsTable from './components/products-table';
import ProductForm from './components/product-form';
import type { Product } from '@/types';
import { MOCK_PRODUCTS } from '@/lib/constants';
import { Search, Filter } from 'lucide-react'; // PlusCircle removed as button is moved
import { useToast } from '@/hooks/use-toast';

@Composable
fun ProductPage_Screen(navController: NavHostController) {

    export default function ProductsPage() {
        const [products, setProducts] = useState<Product[]>(MOCK_PRODUCTS);
        const [searchTerm, setSearchTerm] = useState('');
        const [categoryFilter, setCategoryFilter] = useState('all');
        const [isFormOpen, setIsFormOpen] = useState(false);
        const [editingProduct, setEditingProduct] = useState<Product | null>(null);
        const { toast } = useToast();

        const searchParams = useSearchParams();
        const router = useRouter();

        const categories = useMemo(() => {
        const uniqueCategories = new Set(MOCK_PRODUCTS.map(p => p.category));
        return ['all', ...Array.from(uniqueCategories)];
    }, []);

        const filteredProducts = useMemo(() => {
        return products.filter(product => {
            const matchesSearch = product.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
                    (product.description && product.description.toLowerCase().includes(searchTerm.toLowerCase()));
            const matchesCategory = categoryFilter === 'all' || product.category === categoryFilter;
            return matchesSearch && matchesCategory;
        });
    }, [products, searchTerm, categoryFilter]);

        const handleAddProduct = useCallback(() => {
        setEditingProduct(null);
        setIsFormOpen(true);
    }, []);

        useEffect(() => {
            if (searchParams.get('action') === 'add') {
                handleAddProduct();
                // Clear the query param to prevent re-triggering on refresh if dialog is manually closed
                router.replace('/products', { scroll: false });
            }
            // eslint-disable-next-line react-hooks/exhaustive-deps
        }, [searchParams, router, handleAddProduct]);


        const handleEditProduct = (product: Product) => {
        setEditingProduct(product);
        setIsFormOpen(true);
    };

        const handleDeleteProduct = (productId: string) => {
        setProducts(prev => prev.filter(p => p.id !== productId));
        toast({
            title: "Product Deleted",
            description: `Product with ID ${productId} has been removed.`,
            variant: "destructive",
        });
    };

        const handleFormSubmit = (productData: Product) => {
        const isEditing = !!editingProduct;
        if (isEditing) {
            setProducts(prev => prev.map(p => p.id === productData.id ? productData : p));
            toast({
                title: "Product Updated",
                description: `${productData.name} has been successfully updated.`,
            });
        } else {
            setProducts(prev => [...prev, { ...productData, id: String(Date.now()) }]); // Simple ID generation
            toast({
                title: "Product Added",
                description: `${productData.name} has been successfully added.`,
            });
        }
        setIsFormOpen(false);
        setEditingProduct(null);
    };

        return (
        <div className="space-y-6">
        <div className="flex flex-col md:flex-row justify-between items-center gap-4">
        <h1 className="text-3xl font-semibold">Product Catalogue</h1>
        {/* "Add New Product" button removed from here */}
        </div>

        <div className="flex flex-col md:flex-row gap-4 p-4 border rounded-lg bg-card">
        <div className="relative flex-1">
        <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-5 w-5 text-muted-foreground" />
        <Input
        type="search"
        placeholder="Search products..."
        className="pl-10"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        />
        </div>
        <div className="flex items-center gap-2">
        <Filter className="h-5 w-5 text-muted-foreground" />
        <Select value={categoryFilter} onValueChange={setCategoryFilter}>
        <SelectTrigger className="w-full md:w-[200px]">
        <SelectValue placeholder="Filter by category" />
        </SelectTrigger>
        <SelectContent>
        <SelectGroup>
        <SelectLabel>Categories</SelectLabel>
        {categories.map(category => (
            <SelectItem key={category} value={category}>
                {category === 'all' ? 'All Categories' : category}
            </SelectItem>
            ))}
        </SelectGroup>
        </SelectContent>
        </Select>
        </div>
        </div>

        <ProductsTable
        products={filteredProducts}
        onEdit={handleEditProduct}
        onDelete={handleDeleteProduct}
        />

        <Dialog open={isFormOpen} onOpenChange={(isOpen) => {
        setIsFormOpen(isOpen);
        if (!isOpen) setEditingProduct(null); // Reset editing product when dialog closes
    }}>
        <DialogContent className="sm:max-w-[600px] card-common p-0">
        <DialogHeader className="p-6 pb-0">
        <DialogTitle>{editingProduct ? 'Edit Product' : 'Add New Product'}</DialogTitle>
        <DialogDescription>
            {editingProduct ? 'Update the details of this product.' : 'Fill in the details to add a new product to your catalogue.'}
        </DialogDescription>
        </DialogHeader>
        <div className="p-6 max-h-[70vh] overflow-y-auto">
        <ProductForm
        product={editingProduct}
        onSubmit={handleFormSubmit}
        onCancel={() => setIsFormOpen(false)}
        />
        </div>
        </DialogContent>
        </Dialog>
        </div>
        );
    }

}

@Preview
@Composable
private fun PagePrev() {

    ProductPage_Screen(rememberNavController())

}