package com.visionary_ventures.inventoryvision.ui.theme.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import { useState, useEffect } from 'react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Switch } from '@/components/ui/switch';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Checkbox } from '@/components/ui/checkbox';
import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar';
import { Textarea } from '@/components/ui/textarea';
import { PROFILE_PREFERENCES, CONNECTED_SERVICES } from '@/lib/constants';
import type { UserProfile } from '@/types';
import { Save, ExternalLink, Edit3, Camera } from 'lucide-react';

@Composable
fun ProfilePage_Screen(navController: NavHostController) {

    const initialUserProfile: UserProfile = {
        name: 'Agenda',
        email: 'agenda@ndovufund.com',
        avatarUrl: 'https://placehold.co/150x150.png',
        phone: '+254 722 947 686',
        username: 'None',
        bio: 'Add a few words about yourself',
        preferences: {
        theme: 'dark',
        language: 'en',
        notifications: {
            newProduct: true,
            stockAlerts: true,
            promotions: false,
    },
    },
    };

    export default function ProfilePage() {
        const [userProfile, setUserProfile] = useState<UserProfile>(initialUserProfile);
        const [isEditing, setIsEditing] = useState(false);
        const [currentTheme, setCurrentTheme] = useState<'light' | 'dark'>('dark');

        useEffect(() => {
            const storedTheme = localStorage.getItem('theme') as 'light' | 'dark' || userProfile.preferences.theme;
            setCurrentTheme(storedTheme);
            document.documentElement.classList.toggle('dark', storedTheme === 'dark');
        }, [userProfile.preferences.theme]);

        const handleThemeChange = (isDark: boolean) => {
        const newTheme = isDark ? 'dark' : 'light';
        setCurrentTheme(newTheme);
        setUserProfile(prev => ({ ...prev, preferences: { ...prev.preferences, theme: newTheme } }));
        localStorage.setItem('theme', newTheme);
        document.documentElement.classList.toggle('dark', newTheme === 'dark');
    };

        const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setUserProfile(prev => ({ ...prev, [name]: value }));
    };

        const handleNotificationChange = (id: string, checked: boolean) => {
        setUserProfile(prev => ({
            ...prev,
            preferences: {
            ...prev.preferences,
            notifications: { ...prev.preferences.notifications, [id]: checked },
        },
        }));
    };

        const handleSave = () => {
        setIsEditing(false);
        // In a real app, persist changes to backend
        console.log('Profile saved:', userProfile);
    };

        return (
        <div className="space-y-8">
        <div className="flex justify-between items-center">
        <h1 className="text-3xl font-semibold">User Profile</h1>
        <Button size="lg" onClick={() => isEditing ? handleSave() : setIsEditing(true)}>
            {isEditing ? <><Save className="mr-2 h-5 w-5" /> Save Changes</> : <><Edit3 className="mr-2 h-5 w-5" /> Edit Profile</>}
        </Button>
        </div>

        <Card className="card-common">
        <CardContent className="p-6 space-y-6">
        <div className="flex items-center justify-between">
        <div className="flex items-center gap-4">
        <Avatar className="h-20 w-20">
        <AvatarImage src={userProfile.avatarUrl} alt={userProfile.name} data-ai-hint="user avatar" />
        <AvatarFallback>{userProfile.name?.charAt(0).toUpperCase()}</AvatarFallback>
        </Avatar>
        <div>
            {isEditing ? (
                <Input
                id="name"
                name="name"
                value={userProfile.name || ''}
                onChange={handleInputChange}
                placeholder="Enter your name"
                // Removed custom className to use default Input styling
                />
                ) : (
                <h2 className="text-2xl font-semibold">{userProfile.name}</h2>
                )}
        </div>
        </div>
        <Button variant="default" size="icon" className="rounded-full h-12 w-12 bg-primary text-primary-foreground hover:bg-primary/90">
        <Camera className="h-6 w-6" />
        <span className="sr-only">Change photo</span>
        </Button>
        </div>

        <h3 className="text-xl font-semibold text-primary pt-4">Account</h3>

        <div className="space-y-1">
        <Label htmlFor="phone" className="sr-only">Phone Number</Label>
        {isEditing ? (
            <Input id="phone" name="phone" value={userProfile.phone || ''} onChange={handleInputChange} placeholder="Enter phone number" />
            ) : (
            <p className="text-lg font-medium">{userProfile.phone || 'Not set'}</p>
            )}
        <p className="text-sm text-muted-foreground">
        {isEditing ? 'Your phone number' : 'Tap to change phone number'}
        </p>
        </div>

        <div className="space-y-1">
        <Label htmlFor="username" className="sr-only">Username</Label>
        {isEditing ? (
            <Input id="username" name="username" value={userProfile.username || ''} onChange={handleInputChange} placeholder="Enter username"/>
            ) : (
            <p className="text-lg font-medium">{userProfile.username || 'Not set'}</p>
            )}
        <p className="text-sm text-muted-foreground">Username</p>
        </div>

        <div className="space-y-1">
        <Label htmlFor="bio" className="sr-only">Bio</Label>
        {isEditing ? (
            <Textarea id="bio" name="bio" value={userProfile.bio || ''} onChange={handleInputChange} placeholder="Tell us about yourself" />
            ) : (
            <p className="text-lg whitespace-pre-wrap">{userProfile.bio || 'Not set'}</p>
            )}
        <p className="text-sm text-muted-foreground">Bio</p>
        </div>
        </CardContent>
        </Card>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Preferences</CardTitle>
        <CardDescription>Customize your application experience.</CardDescription>
        </CardHeader>
        <CardContent className="space-y-6">
        <div className="grid md:grid-cols-2 gap-6">
        <div>
        <Label htmlFor="theme">Theme</Label>
        <div className="flex items-center space-x-2 mt-1">
        <Switch
        id="theme"
        checked={currentTheme === 'dark'}
        onCheckedChange={handleThemeChange}
        />
        <span>{currentTheme === 'dark' ? 'Dark Mode' : 'Light Mode'}</span>
        </div>
        </div>
        <div>
        <Label htmlFor="language">Language</Label>
        <Select
        value={userProfile.preferences.language}
        onValueChange={(value) => setUserProfile(prev => ({ ...prev, preferences: { ...prev.preferences, language: value } }))}
        disabled={!isEditing}
        >
        <SelectTrigger id="language">
        <SelectValue placeholder="Select language" />
        </SelectTrigger>
        <SelectContent>
            {PROFILE_PREFERENCES.languages.map(lang => (
                <SelectItem key={lang.value} value={lang.value}>
                <div className="flex items-center gap-2">
                {lang.icon && <lang.icon className="h-4 w-4 text-muted-foreground" />}
                {lang.label}
                </div>
                </SelectItem>
                ))}
        </SelectContent>
        </Select>
        </div>
        </div>
        <div>
        <Label>Notifications</Label>
        <div className="space-y-2 mt-2">
        {PROFILE_PREFERENCES.notifications.map(notif => (
            <div key={notif.id} className="flex items-center space-x-2">
            <Checkbox
            id={notif.id}
            checked={userProfile.preferences.notifications[notif.id] || false}
            onCheckedChange={(checked) => handleNotificationChange(notif.id, !!checked)}
            disabled={!isEditing}
            />
            <Label htmlFor={notif.id} className="font-normal flex items-center gap-2">
            {notif.icon && <notif.icon className="h-4 w-4 text-muted-foreground" />}
            {notif.label}
            </Label>
            </div>
            ))}
        </div>
        </div>
        </CardContent>
        </Card>

        <Card className="card-common">
        <CardHeader>
        <CardTitle>Connected Services</CardTitle>
        <CardDescription>Manage integrations with other platforms.</CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
        {CONNECTED_SERVICES.map(service => (
            <div key={service.id} className="flex items-center justify-between p-3 border rounded-lg">
            <div className="flex items-center gap-3">
            <div className="p-2 bg-muted rounded-md">
            <ExternalLink className="h-5 w-5 text-primary" />
            </div>
            <div>
            <h3 className="font-medium">{service.name}</h3>
            <p className="text-sm text-muted-foreground">{service.description}</p>
            </div>
            </div>
            <Button variant={service.connected ? "destructive" : "default"} size="sm" disabled={!isEditing}>
                    {service.connected ? 'Disconnect' : 'Connect'}
            </Button>
            </div>
            ))}
        </CardContent>
        </Card>
        </div>
        );
    }

}

@Preview
@Composable
private fun PagePrev() {

    ProfilePage_Screen(rememberNavController())

}