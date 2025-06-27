package com.visionary_ventures.inventoryvision.ui.theme.screens.products

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import * as z from 'zod';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Textarea } from '@/components/ui/textarea';
import { Label } from '@/components/ui/label';
import { Form, FormControl, FormDescription, FormField, FormItem, FormLabel, FormMessage } from '@/components/ui/form';
import type { Product } from '@/types';
import { Camera, Upload, Trash2, XCircle, Image as ImageIcon } from 'lucide-react';
import React, { useState, useRef, useEffect, useCallback } from 'react';
import { useToast } from '@/hooks/use-toast';
import { Alert, AlertDescription, AlertTitle } from '@/components/ui/alert';

@Composable
fun ProductForm_Screen(navController: NavHostController) {

    const productSchema = z.object({
        name: z.string().min(3, { message: "Product name must be at least 3 characters." }),
        category: z.string().min(1, { message: "Category is required." }),
        price: z.coerce.number().positive({ message: "Price must be a positive number." }),
        stock: z.coerce.number().int().min(0, { message: "Stock cannot be negative." }),
        description: z.string().optional(),
        imageUrl: z.string().url({ message: "Please enter a valid URL or select an image." }).optional().or(z.literal('')),
        dataAiHint: z.string().optional(),
    });

    type ProductFormData = z.infer<typeof productSchema>;

    interface ProductFormProps {
        product?: Product | null;
        onSubmit: (data: Product) => void;
        onCancel: () => void;
    }

    export default function ProductForm({ product, onSubmit, onCancel }: ProductFormProps) {
        const { toast } = useToast();
        const form = useForm<ProductFormData>({
            resolver: zodResolver(productSchema),
            defaultValues: {
            name: product?.name || '',
            category: product?.category || '',
            price: product?.price || 0,
            stock: product?.stock || 0,
            description: product?.description || '',
            imageUrl: product?.imageUrl || '',
            dataAiHint: product?.dataAiHint || '',
        },
        });

        const [showCameraView, setShowCameraView] = useState(false);
        const [hasCameraPermission, setHasCameraPermission] = useState<boolean | null>(null);
        const [imagePreviewUrl, setImagePreviewUrl] = useState<string | null>(product?.imageUrl || null);

        const videoRef = useRef<HTMLVideoElement>(null);
        const canvasRef = useRef<HTMLCanvasElement>(null);
        const fileInputRef = useRef<HTMLInputElement>(null);
        const streamRef = useRef<MediaStream | null>(null);

        useEffect(() => {
            // Set initial image preview if product has imageUrl
            if (product?.imageUrl) {
                setImagePreviewUrl(product.imageUrl);
            }
        }, [product]);

        const stopCameraStream = useCallback(() => {
        if (streamRef.current) {
            streamRef.current.getTracks().forEach(track => track.stop());
            streamRef.current = null;
        }
        if (videoRef.current) {
            videoRef.current.srcObject = null;
        }
    }, []);

        useEffect(() => {
            const getCameraPermission = async () => {
            if (showCameraView) {
                try {
                    const stream = await navigator.mediaDevices.getUserMedia({ video: true });
                    setHasCameraPermission(true);
                    streamRef.current = stream;
                    if (videoRef.current) {
                        videoRef.current.srcObject = stream;
                    }
                } catch (error) {
                    console.error('Error accessing camera:', error);
                    setHasCameraPermission(false);
                    toast({
                            variant: 'destructive',
                            title: 'Camera Access Denied',
                            description: 'Please enable camera permissions in your browser settings to use this feature.',
                    });
                    setShowCameraView(false); // Close camera view if permission denied
                }
            } else {
                stopCameraStream();
            }
        };

            getCameraPermission();

            return () => {
            stopCameraStream(); // Cleanup on component unmount or when showCameraView changes
        };
        }, [showCameraView, toast, stopCameraStream]);


        const handleTakePhotoClick = () => {
        clearImage(false); // Clear existing image but don't clear form value yet
        setShowCameraView(true);
    };

        const handleCloseCamera = () => {
        setShowCameraView(false);
    };

        const handleCaptureImage = () => {
        if (videoRef.current && canvasRef.current && hasCameraPermission) {
            const video = videoRef.current;
            const canvas = canvasRef.current;
            canvas.width = video.videoWidth;
            canvas.height = video.videoHeight;
            const context = canvas.getContext('2d');
            if (context) {
                context.drawImage(video, 0, 0, canvas.width, canvas.height);
                const dataUri = canvas.toDataURL('image/png');
                setImagePreviewUrl(dataUri);
                form.setValue('imageUrl', dataUri, { shouldValidate: true });
            }
            setShowCameraView(false);
        }
    };

        const handleChooseFileClick = () => {
        clearImage(false); // Clear existing image but don't clear form value yet
        fileInputRef.current?.click();
    };

        const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files?.[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                const dataUri = reader.result as string;
                setImagePreviewUrl(dataUri);
                form.setValue('imageUrl', dataUri, { shouldValidate: true });
            };
            reader.readAsDataURL(file);
        }
        // Reset file input value to allow selecting the same file again
        if (event.target) {
            event.target.value = '';
        }
    };

        const clearImage = (clearFormValue = true) => {
        setImagePreviewUrl(null);
        if (clearFormValue) {
            form.setValue('imageUrl', '', { shouldValidate: true });
        }
        if (showCameraView) {
            setShowCameraView(false);
        }
    };

        const handleSubmit = (data: ProductFormData) => {
        onSubmit({
            ...data,
            id: product?.id || String(Date.now()), // Keep existing ID or generate new
            imageUrl: imagePreviewUrl || data.imageUrl, // Prioritize preview URL (data URI)
        });
    };

        return (
        <Form {...form}>
        <form onSubmit={form.handleSubmit(handleSubmit)} className="space-y-6">
        <FormField
        control={form.control}
        name="name"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Product Name</FormLabel>
            <FormControl>
            <Input placeholder="Enter product name" {...field} />
            </FormControl>
            <FormMessage />
            </FormItem>
            )}
        />
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <FormField
        control={form.control}
        name="category"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Category</FormLabel>
            <FormControl>
            <Input placeholder="e.g., Electronics" {...field} />
            </FormControl>
            <FormMessage />
            </FormItem>
            )}
        />
        <FormField
        control={form.control}
        name="price"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Price</FormLabel>
            <FormControl>
            <Input type="number" step="0.01" placeholder="0.00" {...field} />
            </FormControl>
            <FormMessage />
            </FormItem>
            )}
        />
        </div>
        <FormField
        control={form.control}
        name="stock"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Stock Quantity</FormLabel>
            <FormControl>
            <Input type="number" placeholder="0" {...field} />
            </FormControl>
            <FormMessage />
            </FormItem>
            )}
        />

        {/* Image Upload/Capture Section */}
        <FormItem>
        <FormLabel>Product Image</FormLabel>
        <div className="space-y-4">
        <div className="flex gap-2 flex-wrap">
        <Button type="button" variant="outline" onClick={handleTakePhotoClick}>
        <Camera className="mr-2 h-4 w-4" /> Take Photo
        </Button>
        <Button type="button" variant="outline" onClick={handleChooseFileClick}>
        <Upload className="mr-2 h-4 w-4" /> Choose File
        </Button>
        <input
        type="file"
        ref={fileInputRef}
        onChange={handleFileChange}
        accept="image/*"
        className="hidden"
        />
        <canvas ref={canvasRef} className="hidden" />
        </div>

        {showCameraView && (
            <div className="space-y-2 p-4 border rounded-md">
            <video ref={videoRef} className="w-full aspect-video rounded-md bg-muted" autoPlay playsInline muted />
            {hasCameraPermission === false && (
                <Alert variant="destructive">
                <XCircle className="h-4 w-4" />
                <AlertTitle>Camera Access Denied</AlertTitle>
                <AlertDescription>
                        Please allow camera access in your browser settings to use this feature.
                </AlertDescription>
                </Alert>
                )}
            {hasCameraPermission === null && (
                <Alert>
                <ImageIcon className="h-4 w-4" />
                <AlertTitle>Requesting Camera</AlertTitle>
                <AlertDescription>
                        Attempting to access your camera...
                </AlertDescription>
                </Alert>
                )}
            {hasCameraPermission && (
                <div className="flex gap-2 justify-end">
                <Button type="button" variant="outline" onClick={handleCloseCamera}>Close Camera</Button>
                <Button type="button" onClick={handleCaptureImage}>Capture Image</Button>
                </div>
                )}
            </div>
            )}

        {imagePreviewUrl && !showCameraView && (
            <div className="relative group w-full max-w-xs border rounded-md p-2">
            <img src={imagePreviewUrl} alt="Product preview" className="rounded-md w-full object-contain max-h-60" />
            <Button
            type="button"
            variant="destructive"
            size="icon"
            className="absolute top-2 right-2 opacity-50 group-hover:opacity-100"
            onClick={() => clearImage()}
            >
            <Trash2 className="h-4 w-4" />
            <span className="sr-only">Clear image</span>
            </Button>
            </div>
            )}
        {!imagePreviewUrl && !showCameraView && (
            <div className="p-4 border border-dashed rounded-md text-center text-muted-foreground">
            <ImageIcon className="mx-auto h-8 w-8 mb-2" />
            <p>No image selected</p>
            <p className="text-xs">Take a photo, upload a file, or paste an image URL below.</p>
            </div>
            )}
        </div>
        </FormItem>

        <FormField
        control={form.control}
        name="imageUrl"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Or Paste Image URL (Optional)</FormLabel>
            <FormControl>
            <Input
            placeholder="https://example.com/image.png"
            {...field}
            onChange={(e) => {
                field.onChange(e);
                // If user types a URL, clear any camera/file preview
                if (e.target.value) {
                    setImagePreviewUrl(null);
                    if (showCameraView) setShowCameraView(false);
                }
            }}
            onPaste={(e) => {
                // If user pastes a URL, clear any camera/file preview
                const pastedValue = e.clipboardData.getData('text');
                if (pastedValue) {
                    setImagePreviewUrl(null);
                    if (showCameraView) setShowCameraView(false);
                }
                field.onChange(e); // Allow normal paste behavior
            }}
            />
            </FormControl>
            <FormDescription>If you take/upload a photo, this URL will be ignored.</FormDescription>
            <FormMessage />
            </FormItem>
            )}
        />


        <FormField
        control={form.control}
        name="description"
        render={({ field }) => (
            <FormItem>
            <FormLabel>Description (Optional)</FormLabel>
            <FormControl>
            <Textarea placeholder="Briefly describe the product" {...field} />
            </FormControl>
            <FormMessage />
            </FormItem>
            )}
        />
        <FormField
        control={form.control}
        name="dataAiHint"
        render={({ field }) => (
            <FormItem>
            <FormLabel>AI Image Hint (Optional)</FormLabel>
            <FormControl>
            <Input placeholder="e.g. 'modern gadget' (max 2 words)" {...field} />
            </FormControl>
            <FormDescription>Helpful keywords for placeholder image generation if no image is provided.</FormDescription>
            <FormMessage />
            </FormItem>
            )}
        />
        <div className="flex justify-end gap-4 pt-4">
        <Button type="button" variant="outline" onClick={onCancel}>
            Cancel
        </Button>
        <Button type="submit" size="lg">
        {product ? 'Update Product' : 'Add Product'}
        </Button>
        </div>
        </form>
        </Form>
        );
    }

}

@Preview
@Composable
private fun FormPrev() {

    ProductForm_Screen(rememberNavController())

}